package kr.ac.kumoh.s20201093.w1001intentdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.ac.kumoh.s20201093.w1001intentdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    companion object{ //동반객체 : 같이 가는 객체
        const val keyName = "image"
    }

    private lateinit var binding: ActivityMainBinding

    private lateinit var launcher: ActivityResultLauncher<Intent>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGundam.setOnClickListener(this)
        binding.btnZaku.setOnClickListener(this)


        launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){ //시험?

            if(it.resultCode != RESULT_OK){
                return@registerForActivityResult // 어디서 리턴할지
            }

            val result = it.data?.getIntExtra(ImageActivity.resultName, ImageActivity.NONE)

            val str = when(result){
                ImageActivity.LIKE -> "좋아요"
                ImageActivity.DISLIKE -> "싫어요"
                else -> ""
            }

            val image = it.data?.getStringExtra(ImageActivity.imageNAme)
            when (image) { // 받아온 걸 처리한다.
                "gundam" -> binding.btnGundam.text  =  "건담 ($str)"
                "zaku" -> binding.btnZaku.text  =  "자쿠 ($str)"
            }
        }

    }

    override fun onClick(v: View?) {
        val intent = Intent(this, ImageActivity::class.java)

        val value = when (v?.id){
            binding.btnGundam.id -> "gundam"
            binding.btnZaku.id -> "zaku"
            else -> return
        }

        intent.putExtra(keyName, value)
        //startActivity(intent)
        launcher.launch(intent)  //launcher는 변수, launch는 바뀔 수 없음
    }
}