package com.dewakoding.ar_locationbased

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.dewakoding.arlocationbased.model.Place
import com.dewakoding.arlocationbased.ui.ARActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class MainActivity : ARActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomSheetDialog()

        val list = ArrayList<Place>()
        list.add(
            Place("1",
                BRANCH,
                13.06939192319991,77.79836497028975,
                description = "Shop 1041, Westfield Innaloo Shopping Centre, Oswald Street, INNALOO",
                photoUrl = "https://images.unsplash.com/photo-1606787366850-de6330128bfc?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2940&q=80")
        )
        list.add(
            Place("2",
                ATM,
                12.85091096489551,77.58943746476028,
                description = "T48, Ellenbrook Central, 204 Pinaster Parade, ELLENBROOK",
                photoUrl = "https://images.unsplash.com/photo-1498837167922-ddd27525d352?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2940&q=80")
        )
        list.add(
            Place("3",
                BRANCH,
                13.05639133763296,77.61146780784475,
                description = "Shop G51, Karrinyup Shopping Centre, 200 Karrinyup Rd, KARRINYUP",
                photoUrl = "https://images.unsplash.com/photo-1498837167922-ddd27525d352?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2940&q=80")
        )

        list.add(
            Place("4",
                BRANCH,
                12.95683792955697,77.70114629417182,
                description = "Shop 41/42 Park Shopping Centre 789 Albany Highway, VICTORIA PARK",
                photoUrl = "https://images.unsplash.com/photo-1498837167922-ddd27525d352?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2940&q=80")
        )

        list.add(
            Place("5",
                ATM,
                12.983797532511703,77.7521711052671,
                description = "Shop G51, Karrinyup Shopping Centre, 200 Karrinyup Rd, KARRINYUP",
                photoUrl = "https://images.unsplash.com/photo-1498837167922-ddd27525d352?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2940&q=80")
        )

        list.add(
            Place("6",
                ATM,
                13.055905652435063,77.63228780387284,
                description = "Shop 1041, Westfield Innaloo Shopping Centre, Oswald Street, INNALOO",
                photoUrl = "https://images.unsplash.com/photo-1498837167922-ddd27525d352?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2940&q=80")
        )

        list.add(
            Place("7",
                BRANCH,
                13.101634703135018,77.63492701514357,
                description = "G-017 Belmont Forum Shopping Centre, 227 Belmont Avenue, CLOVERDALE",
                photoUrl = "https://images.unsplash.com/photo-1498837167922-ddd27525d352?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2940&q=80")
        )

        list.add(
            Place("8",
                ATM,
                13.041231453055447,77.51845284507104,
                description = "Shop 41/42 Park Shopping Centre 789 Albany Highway, EAST VICTORIA PARK",
                photoUrl = "https://images.unsplash.com/photo-1498837167922-ddd27525d352?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2940&q=80")
        )

        // You want to display places within a radius of 10.000 meters / 100 km.
        ARInitData(list, 100000.00)

    }

    override fun onARPointSelected(place: Place) {
        //Toast.makeText(applicationContext, place.name, Toast.LENGTH_SHORT).show()
        showBottomSheetDialog(place)
    }

    lateinit var dialog:BottomSheetDialog
    lateinit var bottomSheetDialogView:View
    private fun initBottomSheetDialog() {
        dialog = BottomSheetDialog(this)
        bottomSheetDialogView = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
        val btnClose = bottomSheetDialogView.findViewById<Button>(R.id.idBtnDismiss)
        btnClose.setOnClickListener {
            // on below line we are calling a dismiss
            // method to close our dialog.
            dialog.dismiss()
        }
        dialog.setCancelable(true)
        dialog.setContentView(bottomSheetDialogView)
        //dialog.show()
    }
    private fun showBottomSheetDialog(place: Place) {
        val textViewTitle = bottomSheetDialogView.findViewById<TextView>(R.id.textViewTitle)
        val textViewDescription = bottomSheetDialogView.findViewById<TextView>(R.id.textViewDescription)
        val textViewDistance = bottomSheetDialogView.findViewById<TextView>(R.id.textViewDistance)
        val imageViewLocationType = bottomSheetDialogView.findViewById<ImageView>(R.id.imageViewLocationType)
        textViewTitle.text = place.name
        textViewDescription.text = place.description
        textViewDistance.text = distanceStr(place.distance!!.toDouble())
        if(place.name == ATM){
            imageViewLocationType.setImageDrawable(resources.getDrawable(R.drawable.atm_locations_50))
        } else {
            imageViewLocationType.setImageDrawable(resources.getDrawable(R.drawable.bank_building_64))
        }

        val buttonDirection = bottomSheetDialogView.findViewById<Button>(R.id.buttonDirection)
        buttonDirection.setOnClickListener {
            // on below line we are calling a direction of the locator
            openGoogleMap(place)
            // method to close our dialog.
            dialog.dismiss()
        }

        dialog.setContentView(bottomSheetDialogView)
        dialog.show()
    }
    fun distanceStr(distance: Double): String {
        if (distance < 1000) {
            return (("%.2f".format(distance)) + " m")
        } else {
            return (("%.2f".format(distance / 1000)) + " km")
        }
    }

    private fun openGoogleMap(place: Place) {
        val myData = java.lang.String.format(
            Locale.ENGLISH,
            "http://maps.google.com/maps?daddr=${place.location.latitude},${place.location.longitude}"
        )

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(myData))
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    }

    companion object {
        const val ATM = "ATM"
        const val BRANCH = "Branch"
    }
}