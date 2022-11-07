package com.example.gridlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gridlistapp.databinding.ActivityMainBinding
import com.example.gridlistapp.vewmodel.ImageViewModel

class MainActivity : AppCompatActivity() {
    private var layoutManager: GridLayoutManager? = null
    private lateinit var adapter: ImageAdapter
    private lateinit var viewModel: ImageViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        layoutManager = GridLayoutManager(this, 3)
        binding.recyclerView.layoutManager = layoutManager
        adapter = ImageAdapter(layoutManager)
        binding.recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this)[ImageViewModel::class.java]

        //Observing the data is changed or not
        viewModel.observeMovieLiveData().observe(this, Observer { imageList ->
            binding.loading.visibility = View.GONE
            adapter.setImageList(imageList.data)
        })

        // search Button
        binding.searchBtn.setOnClickListener {
            if (binding.searchEdit.text.isNotEmpty()) {
                 binding.loading.visibility = View.VISIBLE
                viewModel.getSearchImages(binding.searchEdit.text.toString())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.change_layout -> {
                if (layoutManager?.spanCount == 1) {
                    layoutManager?.spanCount = 3
                    item.title = "List"
                } else {
                    layoutManager?.spanCount = 1
                    item.title = "Grid"
                }
                adapter.notifyItemRangeChanged(0, adapter.itemCount ?: 0)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}