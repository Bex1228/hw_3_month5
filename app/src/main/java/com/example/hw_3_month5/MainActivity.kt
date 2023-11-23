package com.example.hw_3_month5

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.hw_3_month5.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var adapter = ImageAdapter(mutableListOf())

    private lateinit var binding: ActivityMainBinding
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()
        setupRecyclerView()
    }

    private fun initClickers() {
        binding.run {
            pageBtn.setOnClickListener {
                page++
                requestimage()
            }

            searchBtn.setOnClickListener {
                page = 1
                requestimage()

            }
            addBtn.setOnClickListener {
                if (adapter.itemCount > page) {
                    adapter.addData(adapter.list[page])
                    page++
                }
            }
        }
    }
    private fun setupRecyclerView() {
        binding.imageRecycler.adapter = adapter
    }

    private fun ActivityMainBinding.requestimage() {
        RetrofitService.api.getImages(keyWord = imageEd.text.toString(), page = page)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            Log.d("ololo", "Received ${it.hits.size} images from the server")
                            if (it.hits.isNotEmpty()) {
                                adapter.addData(it.hits.first())
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("ololo", "onFailure:${t.message} ")
                }
            })
    }
}