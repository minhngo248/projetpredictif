/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


let hr = min = sec = "0" + 0,
        startTimer;

function start() {

    startTimer = setInterval(() => {
        sec++
        sec = sec < 10 ? "0" + sec : sec;

        if (sec == 60) {
            min++;
            min = min < 10 ? "0" + min : min;
            sec = "0" + 0;
        }
        if (min == 60) {
            hr++;
            hr = hr < 10 ? "0" + hr : hr;
            min = "0" + 0;
        }
        putValue();
    }, 1000); //1000ms = 1s

}

/*function stop() {
    startBtn.classList.remove("active");
    stopBtn.classList.add("stopActive");
    clearInterval(startTimer);
} */

function reset() {
//    startBtn.classList.remove("active");
    clearInterval(startTimer);
    hr = min = sec = "0" + 0;
    putValue();
}

function putValue() {
    document.querySelector(".second").innerText = sec;
    document.querySelector(".minute").innerText = min;
    document.querySelector(".hour").innerText = hr;
}