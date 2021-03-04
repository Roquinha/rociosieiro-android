package com.example.applicationrociosieiro.presentation.search

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import com.example.applicationrociosieiro.R
import com.example.applicationrociosieiro.common.entities.artists.ArtistItemEntity
import com.example.applicationrociosieiro.presentation.components.recyclerSearchResults.SearchResultsAdapter
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
        ivSearch.setOnClickListener {
            viewModel.search(etSearch.text.toString())
        }
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

              /*  //ivClearSearchDialog.isVisible = !p0.isNullOrEmpty()

                if (p0.toString().isNotEmpty())
                   // clResults.visibility = View.VISIBLE
                else
                  //  clResults.visibility = View.INVISIBLE
*/
            }
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

    override fun onShareSelected(bulletin: ArtistItemEntity) {
        //Do nothing
    }

    override fun onArtistSelected(bulletin: ArtistItemEntity) {
        //Do nothing
    }
}