package net.decodex.demo.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import coil.load
import net.decodex.demo.R
import net.decodex.demo.data.database.model.User
import net.decodex.demo.databinding.FragmentPersonDetailsBinding
import net.decodex.demo.viewmodels.PersonDetailsViewModel

@AndroidEntryPoint
class PersonDetailsFragment : Fragment() {

    private val viewModel: PersonDetailsViewModel by viewModels()
    private lateinit var binding: FragmentPersonDetailsBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.getUserInfo().observe(viewLifecycleOwner) { setUserInformation(it) }

        return root
    }

    @SuppressLint("SetTextI18n")
    private fun setUserInformation(it: User) {
        binding.personImage.load(it.picture.large) { crossfade(true) }
        binding.personName.text = "${it.name.title} ${it.name.first} ${it.name.last}"
        setFavoriteIcon(it)
        binding.phoneText.text = it.phone
        binding.genderText.text = it.gender
        binding.emailText.text = it.email
        binding.dateOfBirthText.text = it.dateOfBirthInfo.date.toString()
        binding.locationText.text =
            "${it.location.postCode}, ${it.location.city} ${it.location.state}\n${it.location.street.name} ${it.location.street.number}"
        binding.favoriteButton.setOnClickListener { _ -> viewModel.updateUserFavoriteStatus(it) }
    }

    private fun setFavoriteIcon(it: User) {
        if (it.isFavorite) {
            binding.favoriteButton.setImageResource(R.drawable.ic_favorite)
        } else {
            binding.favoriteButton.setImageResource(R.drawable.ic_favorite_border)
        }
    }
}