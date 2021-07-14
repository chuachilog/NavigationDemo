package com.example.navigationdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.navigationdemo.databinding.FragmentQuestionBinding


class QuestionFragment : Fragment() {

    private lateinit var binding : FragmentQuestionBinding

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>

    var questionIndex = 0
    var score: Int = 0

    private val questions: MutableList<Question> = mutableListOf(
        Question(
            text = "what is MCO stand for ?",
            answer = listOf(
                "Movement Control Order",
                "Multiple Choice Order",
                "More Coffee Order"
            )
        ),
        Question(
            text = "what is FMCO?",
            answer = listOf(
                "Full Movement Control Order",
                "Fun Movement Control Order",
                "Forever Movement Control Order"
            )
        ),
    )

    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        answers = currentQuestion.answer.toMutableList()

        answers.shuffle()

        binding.tvQuestion.text = currentQuestion.text
        binding.optA.text = answers[0]
        binding.optB.text = answers[1]
        binding.optC.text = answers[2]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_question, container, false)

        setQuestion()

        binding.btnNext.setOnClickListener{
            val checkedId = binding.radioGroup.checkedRadioButtonId

            if(checkedId != -1) {
                var answerIndex = 0

                when(checkedId) {
                    R.id.optA -> answerIndex = 0
                    R.id.optB -> answerIndex = 1
                    R.id.optC -> answerIndex = 2
                }

                if (answers[answerIndex] == currentQuestion.answer[0]){
                    score +=1
                }

                if (questionIndex < 1){
                    questionIndex += 1
                    binding.radioGroup.clearCheck()
                    setQuestion()
                }else{
                    Navigation.findNavController(it).navigate(R.id.action_questionFragment_to_thankyouFragment)
                }
            }else{
                Toast.makeText(context,"Please Select answer",Toast.LENGTH_LONG).show()
            }


        }

        return binding.root
    }
}