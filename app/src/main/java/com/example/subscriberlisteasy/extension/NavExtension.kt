package com.example.subscriberlisteasy.extension

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.example.subscriberlisteasy.R


// Animção de tela do fragment


private val slideLeftOptions = NavOptions.Builder()
    .setEnterAnim(R.anim.slide_in_right) // animação de entrada
    .setExitAnim(R.anim.slide_out_left) // animação de saída
    .setPopEnterAnim(R.anim.slide_in_left) // animação de pop de entrada
    .setPopExitAnim(R.anim.slide_out_right) // animação pop de saída
    .build()

fun NavController.navigateWithAnimations(
    destinationId: Int,
    animation: NavOptions = slideLeftOptions
) {
    this.navigate(destinationId, null, animation)
}

fun NavController.navigateWithAnimations(
    directions: NavDirections,
    animation: NavOptions = slideLeftOptions
){
    this.navigate(directions,animation)
}