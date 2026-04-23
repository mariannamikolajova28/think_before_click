// Automatické generovanie otázok z priečinkov

function generateQuestions() {
    const questions = [];

    // FRAUD – PHISHING (P0.png - P12.png)
    for (let i = 0; i <= 12; i++) {
        questions.push({
            image: `img/material/Fraud/P${i}.png`,
            correctIsPhishing: true
        });
    }

    // Baiting – B (B1.png - B5.png)
    for (let i = 1; i <= 5; i++) {
        questions.push({
            image: `img/material/Fraud/B${i}.png`,
            correctIsPhishing: true
        });
    }

    // FRAUD – Smashing (S1.png - S4.png)
    for (let i = 1; i <= 4; i++) {
        questions.push({
            image: `img/material/Fraud/S${i}.png`,
            correctIsPhishing: true
        });
    }

    // FRAUD – Tailgaiting (T1.png - T6.png)
    for (let i = 1; i <= 5; i++) {
        questions.push({
            image: `img/material/Fraud/T${i}.png`,
            correctIsPhishing: true
        });
    }

    // FRAUD – SN (SN1.png - SN.png) - baiting
    for (let i = 1; i <= 1; i++) {
        questions.push({
            image: `img/material/Fraud/SN${i}.png`,
            correctIsPhishing: true
        });
    }

    // REAL (R0.png - R2.png) - phising
    for (let i = 0; i <= 2; i++) {
        questions.push({
            image: `img/material/Real/R${i}.png`,
            correctIsPhishing: false
        });
    }

    //Baiting real
    for (let i = 1; i <= 4; i++) {
        questions.push({
            image: `img/material/Real/RB${i}.png`,
            correctIsPhishing: false
        });
    }

    return questions;
}

window.QUESTIONS = generateQuestions();