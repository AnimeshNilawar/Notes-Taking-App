package com.dialiax.notetakingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.dialiax.notetakingapp.database.NoteDatabase
import com.dialiax.notetakingapp.databinding.ActivityMainBinding
import com.dialiax.notetakingapp.repository.NoteRepository
import com.dialiax.notetakingapp.viewmodel.NoteViewModel
import com.dialiax.notetakingapp.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()


    }

    private fun setUpViewModel() {
        val noteRepository = NoteRepository(NoteDatabase(this))

        val viewModelProviderFactory = NoteViewModelFactory(application, noteRepository)

        noteViewModel = ViewModelProvider(this,viewModelProviderFactory).get(NoteViewModel::class.java)
    }
}