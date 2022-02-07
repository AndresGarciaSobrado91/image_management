package com.garcia.image_management.presentation.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.garcia.image_management.R

import com.garcia.image_management.databinding.FragmentPhotosBinding

import com.garcia.image_management.presentation.photo.adapter.RVAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment : Fragment() {

    private val binding: FragmentPhotosBinding by lazy {
        FragmentPhotosBinding.inflate(layoutInflater)
    }

    //private lateinit var viewModel : PhotosViewModel
    private val viewModel: PhotosViewModel by viewModels() // Fragment KTX

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[PhotosViewModel::class.java] // Alternative without Fragment KTX
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            viewModel.toggleImageProvider()
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.stateLiveData.observe(viewLifecycleOwner){ result ->

            result.photos?.let {
                initRecyclerView()
            }

            result.imageProvider?.let {
                initRecyclerView()
                binding.textView.text = when (it){
                    RVAdapter.ImageProvider.Coil -> "COIL"
                    RVAdapter.ImageProvider.Glide -> "GLIDE"
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.rvMain.apply {
            layoutManager = GridLayoutManager(requireContext(),2)
            setHasFixedSize(true)
            val uiAdapter = RVAdapter(viewModel.photoList,viewModel.currentImageProvider){ photoId ->
              Toast.makeText(requireContext(),"ImageId: $photoId", Toast.LENGTH_SHORT).show()
            }
            adapter = uiAdapter
        }
    }
}