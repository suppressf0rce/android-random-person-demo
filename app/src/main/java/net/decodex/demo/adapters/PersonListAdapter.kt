package net.decodex.demo.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.transform.CircleCropTransformation
import coil.load
import net.decodex.demo.data.database.model.User
import net.decodex.demo.databinding.ItemPersonBinding

class PersonListAdapter : RecyclerView.Adapter<PersonListAdapter.PersonViewHolder>() {

    private var data: MutableList<User> = ArrayList()
    var onUserClicked: ((User) -> Unit)? = null

    fun setUsers(users: List<User>) {
        val diffCallback = UsersCallback(data, users)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        data.clear()
        data.addAll(users)
        diffResult.dispatchUpdatesTo(this)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PersonViewHolder {
        // Create a new view, which defines the UI of the list item
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemPersonBinding.inflate(inflater, viewGroup, false)
        return PersonViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: PersonViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.bind(data[position])
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    inner class PersonViewHolder(private val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(user: User) {
            binding.personName.text = "${user.name.title} ${user.name.first} ${user.name.last}"
            binding.personPicture.load(user.picture.thumbnail) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            binding.root.setOnClickListener {
                onUserClicked?.invoke(user)
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = data.size


    class UsersCallback(private val oldList: List<User>, private val newList: List<User>) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].email == newList[newItemPosition].email
        }

        override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            val oldUser = oldList[oldPosition]
            val newUser = newList[newPosition]
            return oldUser == newUser && oldUser.name == newUser.name
        }
    }
}