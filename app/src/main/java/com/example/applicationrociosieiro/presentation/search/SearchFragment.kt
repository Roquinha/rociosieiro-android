package com.example.applicationrociosieiro.presentation.search

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.applicationrociosieiro.R
import com.example.applicationrociosieiro.common.entities.artists.ArtistItemEntity
import com.example.applicationrociosieiro.presentation.components.recyclerSearchResults.SearchResultsAdapter
import com.example.applicationrociosieiro.presentation.search.artistdetail.ArtistDetailViewEntry
import com.example.base.presentation.BaseFragment
import kotlinx.android.synthetic.main.search_fragment_layout.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A [Fragment] to search artists by name.
 */
@ExperimentalCoroutinesApi
class SearchFragment : BaseFragment<SearchFragmentState, SearchFragmentTransition>(), SearchResultsAdapter.OnActionCallback{
    override val layoutRes = R.layout.search_fragment_layout
    override val viewModel by viewModel<SearchFragmentViewModel>()

    override fun initViews() {
        initAdapter()
        etSearch?.requestKeyboardFocus()
        ivSearch.setOnClickListener {
            etSearch?.clearKeyboardFocus()
            viewModel.search(etSearch.text.toString())
        }

        etSearch.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE && v.text.isNotEmpty()) {
                etSearch?.clearKeyboardFocus()
            }
            true
        }

        etSearch.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                etSearch?.clearKeyboardFocus()
                viewModel.search(etSearch.text.toString())
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun initAdapter(){
        rvResults.update(listOf(), this@SearchFragment)
    }

    override fun manageState(state: SearchFragmentState) {
        when(state) {
            is SearchFragmentState.Results -> {
                if(state.artistList.isEmpty())
                    showEmptyState(true)
                else{
                    showEmptyState(false)
                    rvResults.update(state.artistList, this@SearchFragment)
                }
            }

            is SearchFragmentState.EmptyState -> showEmptyState(true)
            is SearchFragmentState.ErrorConexion -> showErrorConexionDialog()
        }
    }

    private fun showErrorConexionDialog(){

    }

    private fun showEmptyState(show: Boolean){
        if(show){
            tvNoResults.visibility = View.VISIBLE
            rvResults.visibility = View.INVISIBLE
        }else{
            tvNoResults.visibility = View.INVISIBLE
            rvResults.visibility = View.VISIBLE
        }
    }

    override fun manageTransition(transition: SearchFragmentTransition) {

    }

    override fun onShareSelected(artist: ArtistItemEntity) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, artist.externalUrls?.spotify ?: artist.name)
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
        context?.let {
            ContextCompat.startActivity(
                it,
                Intent.createChooser(shareIntent, null),
                null
            )
        }
    }

    override fun onArtistSelected(id: String) {
        findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToArtistDetailFragment(ArtistDetailViewEntry.ArtistEntry(id)))
    }

    private fun EditText.requestKeyboardFocus() {
        requestFocus()
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun EditText.clearKeyboardFocus() {
        clearFocus()
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
}