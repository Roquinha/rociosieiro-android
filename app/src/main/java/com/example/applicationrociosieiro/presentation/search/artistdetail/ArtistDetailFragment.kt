package com.example.applicationrociosieiro.presentation.search.artistdetail

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.example.applicationrociosieiro.R
import com.example.base.presentation.BaseFragment
import kotlinx.android.synthetic.main.artist_detail_fragment_layout.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * A [Fragment] to show artist detail.
 */
@ExperimentalCoroutinesApi
class ArtistDetailFragment : BaseFragment<ArtistDetailFragmentState, ArtistDetailFragmentTransition>() {
    override val layoutRes = R.layout.artist_detail_fragment_layout
    private val args by navArgs<ArtistDetailFragmentArgs>()
    override val viewModel by viewModel<ArtistDetailFragmentViewModel> {
        parametersOf(args.entryArg)
    }

    override fun initViews() {
        initAdapter()
    }

    private fun initAdapter() {
        (rvAlbums.layoutManager as? GridLayoutManager)?.let { layoutManager ->
            rvAlbums.init(layoutManager)
        }

        ivLessZoom.setOnClickListener {
            viewModel.lessZoom()
        }
        ivMoreZoom.setOnClickListener {
            viewModel.moreZoom()
        }
    }

    override fun manageState(state: ArtistDetailFragmentState) {
        when(state) {
            is ArtistDetailFragmentState.ArtistDetail -> {
                tvArtist.text = state.artistDetail.name
                var textGenre = ""
                state.artistDetail.genres?.let {
                    for (genre in it) {
                        textGenre += genre
                    }

                    tvGenre.text = textGenre
                }

                if(!state.artistDetail.images.isNullOrEmpty()){
                ivArtist.load(state.artistDetail.images!![0].url?: "")
                }
            }

            is ArtistDetailFragmentState.ArtistAlbums -> manageState(state)
            is ArtistDetailFragmentState.EmptyState -> showEmptyState(true)
            is ArtistDetailFragmentState.ErrorConexion -> showErrorConexionDialog()
        }
    }

    private fun showErrorConexionDialog(){

    }

    private fun manageState(state: ArtistDetailFragmentState.ArtistAlbums) {
        if(state.albumsList.isEmpty())
            showEmptyState(true)
        else{
            showEmptyState(false)
            rvAlbums.update(state.albumsList)
        }
    }

    private fun showEmptyState(show: Boolean){
        if(show){
            tvNoResults.visibility = View.VISIBLE
            rvAlbums.visibility = View.INVISIBLE
            ivLessZoom.visibility = View.INVISIBLE
        }else{
            tvNoResults.visibility = View.INVISIBLE
            rvAlbums.visibility = View.VISIBLE
            ivLessZoom.visibility = View.VISIBLE
        }
    }

    override fun manageTransition(transition: ArtistDetailFragmentTransition) {

    }

}