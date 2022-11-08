package kr.ac.kumoh.s20201093.w1001intentdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import kr.ac.kumoh.s20201093.w1001intentdata.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityImageBinding

    private var image: String? = null

    companion object {
        const val imageNAme = "image"
        const val resultName = "result"

        const val LIKE = 10
        const val DISLIKE = 20
        const val NONE = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toast.makeText(this, intent.getStringExtra("image"),Toast.LENGTH_LONG).show()

        image = intent.getStringExtra(MainActivity.keyName)
        val res = when(image) {
            "gundam" -> R.drawable.pig
            "zaku" -> R.drawable.tyty
            else -> R.drawable.ic_launcher_foreground
        } // 밑에 있는 이미지 뜨기를 when으로 바꿈
        binding.imgGundam.setImageResource(res)

//        if(intent.getStringExtra("image") == "gundam")
//            binding.imgGundam.setImageResource(R.drawable.pig) // 이미지 뜨기

        binding.btnLike.setOnClickListener(this)
        binding.btnDislike.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val intent = Intent()
        val value = when (v?.id) {
            binding.btnLike.id -> LIKE
            binding.btnDislike.id -> DISLIKE
            else -> NONE
        }
        intent.putExtra(imageNAme,image)
        intent.putExtra(resultName, value)
        setResult(RESULT_OK, intent)
        finish()
    }
}