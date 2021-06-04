package com.example.capstonemobile.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.capstonemobile.databinding.ActivityLoginBinding
import com.example.capstonemobile.ui.MainActivity
import com.example.capstonemobile.utils.SessionManagement
import com.ojanbelajar.moviekatalogue.utils.Status
import okhttp3.RequestBody
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private lateinit var session: SessionManagement
    private lateinit var binding: ActivityLoginBinding
    private lateinit var body: RequestBody
    private val model: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        session = SessionManagement(applicationContext)
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        login()
    }

    private fun login(){
        val button = binding.btnLogin
        button.setOnClickListener {
            body = createJsonRequestBody(
                "username" to binding.etEmail.text.toString(),
                "password" to binding.etPassword.text.toString(),
                "role" to "ROLE_USER"
            )
            model.login(body).observe(this, Observer { user ->
                if (user != null){
                    when(user.status){
                        Status.SUCCESS -> {
                            session.createLogin(user.data?.email.toString(),user.data?.password.toString())
                            startActivity<MainActivity>()
                        }
                        Status.ERROR -> {
                            toast("Something Wrong")
                        }
                    }
                }

            })

        }
    }

    private fun createJsonRequestBody(vararg params: Pair<String, String>) =
        RequestBody.create(
            okhttp3.MediaType.parse("application/json"),
            JSONObject(mapOf(*params)).toString()
        )

}