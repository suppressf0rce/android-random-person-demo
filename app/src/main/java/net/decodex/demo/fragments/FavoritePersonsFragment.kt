package net.decodex.demo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import net.decodex.demo.adapters.PersonListAdapter
import net.decodex.demo.data.Status
import net.decodex.demo.data.database.model.User
import net.decodex.demo.databinding.FragmentFavoritePersonsBinding
import net.decodex.demo.viewmodels.FavoritePersonsViewModel

@AndroidEntryPoint
class FavoritePersonsFragment : Fragment() {

    private var _binding: FragmentFavoritePersonsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: FavoritePersonsViewModel by viewModels()
    private val personListAdapter = PersonListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        personListAdapter.onUserClicked = {
            navigateToPersonDetails(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritePersonsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecycler()

        viewModel.getFavoritePersons().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> showLoadingProgress()
                Status.SUCCESS -> {
                    personListAdapter.setUsers(it.data ?: ArrayList())
                    hideLoadingProgress()
                }
                Status.ERROR -> {
                    Snackbar.make(binding.root, it.message ?: "", Snackbar.LENGTH_LONG).show()
                    hideLoadingProgress()
                }
            }
        }

        return root
    }

    private fun showLoadingProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoadingProgress() {
        binding.progressBar.visibility = View.GONE
    }

    private fun setupRecycler() {
        binding.personsRecyclerView.apply {
            adapter = personListAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun navigateToPersonDetails(user: User) {
        val action =
            FavoritePersonsFragmentDirections.actionNavFavoritesToPersonDetailsFragment(user.email)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}