package com.example.gridlistapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gridlistapp.data.Data
import com.example.gridlistapp.databinding.GridItemLayoutBinding
import com.example.gridlistapp.databinding.ListItemLayoutBinding
import java.text.SimpleDateFormat

class ImageAdapter(
    private val layoutManager: GridLayoutManager? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var imageList = ArrayList<Data>()

    enum class ViewType {
        SMALL,
        DETAILED
    }

    fun setImageList(movieList : List<Data>){
        this.imageList = movieList as ArrayList<Data>
        notifyDataSetChanged()
    }


    //View Holder for ListView Arrangement
    inner class ListImageViewHolder(private val detailsBind: ListItemLayoutBinding) :
        RecyclerView.ViewHolder(detailsBind.root) {
        fun bind(imageList: Data) {
            try {
                if (imageList.images.isNotEmpty()) {
                    for (image in imageList.images) {
                        if (image.type == "image/jpeg") {
                            Glide.with(detailsBind.root)
                                .load(image.link)
                                .into(detailsBind.image)

                            // for getting the count of the image
                            detailsBind.title.text = "Image Count : " +imageList.images.filter { image -> image.type == "image/jpeg" }.size.toString()
                            detailsBind.number.text = image.size.toString()
                            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
                            val dateString = simpleDateFormat.format(image.datetime)
                            detailsBind.date.text = dateString

                        }
                    }
                }
            } catch (e:Exception) {
                //ignore
            }
        }
    }

    //ViewHolder for Grid View Arrangment

    inner class GridImageViewHolder(private val listbind: GridItemLayoutBinding) :
        RecyclerView.ViewHolder(listbind.root) {
        fun bind(imageList: Data) {
            try {
                if (imageList.images.isNotEmpty()) {
                    for (image in imageList.images) {
                        if (image.type == "image/jpeg") {
                            Glide.with(listbind.root)
                                .load(image.link)
                                .into(listbind.image)
                            listbind.title.text = imageList.title
                            // for getting the count of the image
                            listbind.number.text = "Image Count : " + imageList.images.filter { image -> image.type == "image/jpeg" }.size.toString()
                            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
                            val dateString = simpleDateFormat.format(image.datetime)
                            listbind.date.text = dateString
                        }
                    }
                }
            }catch (e:Exception){
                //ignore
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.DETAILED.ordinal -> {
               val  view  =  ListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                             ListImageViewHolder(view)
            }
            else -> {
                val  view  =  GridItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                             GridImageViewHolder(view)
            }
        }
    }
    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        when (getItemViewType(p1)) {
            ViewType.DETAILED.ordinal -> {
                //for ListView
                (p0 as ListImageViewHolder).bind(imageList[p1])
            }
            else -> {
                //for GridView
                (p0 as GridImageViewHolder).bind(imageList[p1])
            }
        }
    }

    override fun getItemCount() = imageList.size

    override fun getItemViewType(position: Int): Int {
        //return layout with the spancount click from activity
        return if (layoutManager?.spanCount == 1) ViewType.DETAILED.ordinal
        else ViewType.SMALL.ordinal
    }
}