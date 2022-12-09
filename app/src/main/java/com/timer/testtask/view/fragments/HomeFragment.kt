package com.timer.testtask.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.timer.testtask.R
import com.timer.testtask.data.common.TIME
import com.timer.testtask.data.common.TIME_X2
import com.timer.testtask.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            startTimerButton.setOnClickListener {
                if (!viewModel.isTimerRunning) {
                    viewModel.startTimer()
                    startTimerButton.text = getString(R.string.stop)
                } else {
                    viewModel.stopTimer()
                    startTimerButton.text = getString(R.string.start)
                }
                viewModel.progressLiveData.observe(viewLifecycleOwner) { seconds ->
                    val hours = seconds / TIME_X2
                    val min = seconds / TIME - hours * TIME
                    val sec = seconds - min * TIME - hours * TIME_X2
                    timerResultTextview.text = getString(R.string.timer_stamp, hours, min, sec)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.stopTimer()
        _binding = null
    }
}