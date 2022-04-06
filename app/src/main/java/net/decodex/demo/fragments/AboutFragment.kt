package net.decodex.demo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mehdi.sakout.aboutpage.AboutPage
import mehdi.sakout.aboutpage.Element
import net.decodex.demo.BuildConfig
import net.decodex.demo.R


class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val developersElement = Element()
        developersElement.title = "Developers"
        developersElement.value = "Dejan Radmanovic"

        val shareElement = Element()
        shareElement.title = "Share"

        val aboutPage = AboutPage(context)
            .isRTL(false)
            .setImage(R.mipmap.ic_launcher)
            .setDescription("This App has been developed for the Job Interview landing, and demo purposes\nCopyright: Dejan Radmanovic")
            .addItem(Element("Version " + BuildConfig.VERSION_NAME, R.drawable.ic_info))
            .addGroup("Connect with us")
            .addGitHub("suppressf0rce/android-random-person-demo")
            .addPlayStore(requireContext().packageName)
            .addWebsite("https://www.linkedin.com/in/dejan-radmanovic-414436170/")
            .addFacebook("suppressf0rce")
            .addEmail("office@decodex.net")
            .addItem(developersElement)
            .addItem(shareElement)

        return aboutPage.create()
    }
}