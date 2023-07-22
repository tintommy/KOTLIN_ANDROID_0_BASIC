package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.retrofitdemo.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var retService = RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getRequestWithPathparameters()
        getRequestWithQueryparameters()

        /* val retService = RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)

         val responseLiveData: LiveData<Response<Albums>> = liveData {
             val response: Response<Albums> = retService.getAlbums()
             emit(response)
         }
         responseLiveData.observe(this, Observer {
             val albumsList: MutableListIterator<AlbumsItem>? = it.body()?.listIterator()
             if (albumsList != null) {
                 while (albumsList.hasNext()) {
                     val albumsItem: AlbumsItem = albumsList.next()
                     val result = " " + "Album Title: ${albumsItem.title} \n" +
                             "Album id: ${albumsItem.id} \n" +
                             "Album Userid: ${albumsItem.userId} \n\n\n"
                     binding.textView4.append(result)

                 }
             }
         })
 */


    }

    private fun getRequestWithQueryparameters(){
        CoroutineScope(Dispatchers.IO).launch {
            //path parameter example

//            val response = retService.getAlbums()
            /* val userIdsList = listOf(1, 2, 3) // Ví dụ danh sách userId
             val response = retService.getSortedAlbums(userIdsList)*/
            val response = retService.getSortedAlbums(3) //lấy album
            if (response.isSuccessful) {
                val albumsList = response.body() // Lấy danh sách album từ phản hồi API
                withContext(Dispatchers.Main) {

                    if (albumsList != null) {
                        for (i in 0 until albumsList.size) {
                            val albumsItem: AlbumsItem = albumsList[i]
                            val result = " " + "Album Title: ${albumsItem.title} \n" +
                                    " id: ${albumsItem.id} \n" +
                                    " Userid: ${albumsItem.userId} \n\n\n"
                            binding.textView4.append(result)

                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            binding.textView4.setText("List is Null")

                        }
                    }

                }

            } else {
                withContext(Dispatchers.Main) {
                    binding.textView4.setText("Lấy API không thành công")

                }
            }
        }
    }

    private fun getRequestWithPathparameters(){
        CoroutineScope(Dispatchers.IO).launch {
            //path parameter example
            val response = retService.getAlbum(3) //lấy album
            if (response.isSuccessful) {
                val albums = response.body() // Lấy danh sách album từ phản hồi API
                withContext(Dispatchers.Main) {

                    Toast.makeText(application, "${albums?.title}", Toast.LENGTH_SHORT).show()


                }

            } else {
                withContext(Dispatchers.Main) {
                    binding.textView4.setText("Lấy API không thành công")

                }
            }
        }
    }

}