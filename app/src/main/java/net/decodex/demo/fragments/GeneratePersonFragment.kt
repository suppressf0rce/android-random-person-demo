package net.decodex.demo.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import net.decodex.demo.data.Status
import net.decodex.demo.data.database.model.User
import net.decodex.demo.databinding.FragmentGeneratePersonBinding
import net.decodex.demo.viewmodels.GeneratePersonViewModel

@AndroidEntryPoint
class GeneratePersonFragment : Fragment() {

    private var _binding: FragmentGeneratePersonBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: GeneratePersonViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeneratePersonBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.buttonGenerate.setOnClickListener {
            onGenerateButtonClick()
        }

        return root
    }

    private fun onGenerateButtonClick() {
        viewModel.generateUser().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> showProgressBar()
                Status.ERROR -> showError(it.message)
                Status.SUCCESS -> navigateToPersonDetails(it.data)
            }
        }
    }

    private fun navigateToPersonDetails(user: User?) {
        hideProgressBar()
        user?.let {
            val action =
                GeneratePersonFragmentDirections.actionNavGenerateToPersonDetailsFragment(user.email)
            findNavController().navigate(action)
        }
    }

    private fun showError(exception: String?) {
        Snackbar.make(binding.root, "Error Generating user...", Snackbar.LENGTH_SHORT).show()
        Log.e(TAG, exception ?: "Error Generating User")
        hideProgressBar()
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "GeneratePersonFragment"
    }
}