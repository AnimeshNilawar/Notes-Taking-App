package com.dialiax.notetakingapp.adapter


import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dialiax.notetakingapp.databinding.NoteLayoutBinding
import com.dialiax.notetakingapp.fragments.homeFragmentDirections
import com.dialiax.notetakingapp.model.Note
import java.util.*

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

        class NoteViewHolder(val itemBinding: NoteLayoutBinding):
                RecyclerView.ViewHolder(itemBinding.root)

        private val differCallBack = object : DiffUtil.ItemCallback<Note>(){
                override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                        return oldItem.id == newItem.id &&
                                oldItem.noteBody == newItem.noteBody &&
                                oldItem.noteTitle == newItem.noteTitle
                }

                override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                        return oldItem == newItem
                }
        }

        val differ = AsyncListDiffer(this,differCallBack)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
                return NoteViewHolder(
                        NoteLayoutBinding.inflate(
                                LayoutInflater.from(parent.context),parent,false
                        )
                )
        }

        override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
                val currentnote = differ.currentList[position]

                holder.itemBinding.tvNoteTitle.text = currentnote.noteTitle
                holder.itemBinding.tvNoteBody.text = currentnote.noteBody

                val random = Random()
                val color = Color.argb(
                        255,
                        random.nextInt(256),
                        random.nextInt(256),
                        random.nextInt(256)
                )

                holder.itemBinding.ibColor.setBackgroundColor(color)

                holder.itemView.setOnClickListener {

                        val direction = homeFragmentDirections.actionHomeFragmentToUpdateNoteFragment(currentnote)

                        it.findNavController().navigate(direction)


                }

        }

        override fun getItemCount(): Int {
                return differ.currentList.size
        }
}