package com.beer.presentation.view.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.activityViewModels
import com.beer.presentation.extension.loadUrl
import com.beer.presentation.viewmodel.BeerViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mido.beers.R
import com.mido.beers.databinding.FragmentBottomSheetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetDetailsFragment : BottomSheetDialogFragment() {

    private val viewModel: BeerViewModel by activityViewModels()
    private lateinit var binding: FragmentBottomSheetBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener { setupHeight(it as BottomSheetDialog) }
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initScreen()
        setupButtons()
    }

    private fun initScreen() {
        binding.tvBeerName.text = viewModel.beerSelected.name
        binding.imgUrlBeer.loadUrl(viewModel.beerSelected.imageURL)
        binding.tvBeerDescription.text = viewModel.beerSelected.description
        binding.tvBeerAbv.text = viewModel.beerSelected.abvScreen
        binding.tvBeerIbu.text = viewModel.beerSelected.ibu.toString()
    }

    private fun setupButtons() {
        binding.btnClose.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }

    private fun setupHeight(bottomSheetDialog: BottomSheetDialog) {
        val frameLayout = bottomSheetDialog.findViewById<FrameLayout>(R.id.design_bottom_sheet)
        val behavior = frameLayout?.let { BottomSheetBehavior.from(it) }
        frameLayout?.background =
            ResourcesCompat.getDrawable(resources, R.drawable.bg_bottomsheet_dialog, null)
        behavior?.state = BottomSheetBehavior.STATE_COLLAPSED
    }
}