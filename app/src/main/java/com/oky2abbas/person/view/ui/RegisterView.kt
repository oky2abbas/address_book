package com.oky2abbas.person.view.ui

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.location.Location
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.oky2abbas.ktx.widget.isPermission
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.oky2abbas.person.R
import com.oky2abbas.person.common.base.BaseActivity
import com.oky2abbas.person.common.ext.get
import com.oky2abbas.person.common.ext.getVectorBitmapDescriptor
import com.oky2abbas.person.common.ext.go
import com.oky2abbas.person.common.ext.showMessage
import com.oky2abbas.person.domain.model.LitePerson
import com.oky2abbas.person.view.state.ViewState
import com.oky2abbas.person.viewModel.PersonVM
import kotlinx.android.synthetic.main.register_info_view.*
import kotlinx.android.synthetic.main.register_location_view.*
import kotlinx.android.synthetic.main.register_view.*
import javax.inject.Inject


class RegisterView : BaseActivity() {
    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private var googleMap: GoogleMap? = null

    private val personVM by lazy { vmFactory.get(this, PersonVM::class.java) }
    private val person by lazy { LitePerson() }
    private val fusedLocation by lazy { LocationServices.getFusedLocationProviderClient(this) }

    override fun layoutRes(): Int {
        return R.layout.register_view
    }

    override fun viewHandler(savedInstanceState: Bundle?) {
        observerError()
        observerAddedPerson()

        configGender()
        goToNextStage()
        listenToSubmit()
    }

    private fun configGender() = segGender {
        initialCheckedIndex = 0
        onSegmentChecked { segment ->
            person.gender = segment.text.toString()
        }
    }

    private fun goToNextStage() = btnNextStage.setOnClickListener {
        val emptyError = getString(R.string.str_is_empty)
        person.apply {
            edtFirstName.editText?.let {
                if (it.text.isEmpty()) {
                    it.error = emptyError
                    return@setOnClickListener
                } else firstName = it.text.toString()
            }

            edtLastName.editText?.let {
                if (it.text.isEmpty()) {
                    it.error = emptyError
                    return@setOnClickListener
                } else lastName = it.text.toString()
            }

            edtMobileNumber.editText?.let {
                if (it.text.isEmpty()) {
                    it.error = emptyError
                    return@setOnClickListener
                } else coordinateMobile = it.text.toString()
            }

            edtPhoneNumber.editText?.let {
                if (it.text.isEmpty()) {
                    it.error = emptyError
                    return@setOnClickListener
                } else coordinatePhoneNumber = it.text.toString()
            }

            edtAddress.editText?.let {
                if (it.text.isEmpty()) {
                    it.error = emptyError
                    return@setOnClickListener
                } else address = it.text.toString()
            }

            if (!isPermission(arrayOf(ACCESS_FINE_LOCATION)))
                return@setOnClickListener

            flpRegister.go(ViewState.Two.index)
            listenToLoadMap()
        }
    }

    private fun listenToLoadMap() = (frgMap as SupportMapFragment).getMapAsync {
        googleMap = it
        listenToSelectLocation()
        listenToGetLastLocation()
        listenToRequestLocation()
    }

    private fun listenToSelectLocation() = googleMap?.setOnMapClickListener {
        googleMap?.clear()
        googleMap?.addMarker(myMarkerOption(it))
    }

    private fun listenToGetLastLocation() =
        fusedLocation.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                val latLng = LatLng(it.latitude, it.longitude)
                googleMap?.clear()
                googleMap?.addMarker(myMarkerOption(latLng))
                googleMap?.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(latLng, 16f)
                )
            }
        }

    private fun listenToRequestLocation() = fabGetMyLocation.setOnClickListener {
        listenToGetLastLocation()
    }

    private fun myMarkerOption(location: LatLng) = MarkerOptions().apply {
        position(location)
        icon(getVectorBitmapDescriptor(R.drawable.ico_location_marker))
        title(getString(R.string.str_my_location))
    }

    private fun listenToSubmit() = btnSubmit.setOnClickListener {
        flpRegister.go(ViewState.Three.index)
        personVM.addPerson(person)
    }

    private fun observerAddedPerson() = personVM.livePerson()
        .observe(this, Observer {
            showMessage(
                String.format(
                    getString(R.string.str_save_message_format),
                    "${it.firstName} ${it.lastName}"
                )
            ).also { finish() }
        })

    private fun observerError() = personVM.liveError()
        .observe(this, Observer {
            showMessage(it)
        })
}