let wrap01 = document.querySelector(".wrap01");
let wrap02 = document.querySelector(".wrap02");
let wrap03 = document.querySelector(".wrap03");
let wrap04 = document.querySelector(".wrap04");
let target01 = document.querySelector(".target01");
let target02 = document.querySelector(".target02");
let target03 = document.querySelector(".target03");
let target04 = document.querySelector(".target04");

window.onscroll = function (e) {
    let tCon = document.getElementById("target-container");
    let height = tCon.getBoundingClientRect().y;
    if (height > 420) {
        gsap.to(".wrap01", {
            opacity: 0,
            zIndex: 0,
            duration: 0.75,
        });
        gsap.to(
            ".wrap02",
            {
                opacity: 0,
                zIndex: 0,
                duration: 0.75,
            },
            "<"
        );
        gsap.to(
            ".wrap03",
            {
                opacity: 0,
                zIndex: 0,
                duration: 0.75,
            },
            "<"
        );
        gsap.to(".wrap04", {
            opacity: 0,
            zIndex: 0,
            duration: 0.75,
        });
    }
    if (height < 420 && height > -343) {
        gsap.to(".wrap01", {
            opacity: 1,
            zIndex: 2,
            duration: 0.75,
        });
        gsap.to(
            ".wrap02",
            {
                opacity: 0,
                zIndex: 0,
                duration: 0.75,
            },
            "<"
        );
        gsap.to(
            ".wrap03",
            {
                opacity: 0,
                zIndex: 0,
                duration: 0.75,
            },
            "<"
        );
        gsap.to(".wrap04", {
            opacity: 0,
            zIndex: 0,
            duration: 0.75,
        });
    }
    if (height < -343 && height > -1343) {
        gsap.to(".wrap01", {
            opacity: 0,
            zIndex: 0,
            duration: 0.75,
        });
        gsap.to(
            ".wrap02",
            {
                opacity: 1,
                zIndex: 2,
                duration: 0.75,
            },
            "<"
        );
        gsap.to(
            ".wrap03",
            {
                opacity: 0,
                zIndex: 0,
                duration: 0.75,
            },
            "<"
        );
        gsap.to(".wrap04", {
            opacity: 0,
            zIndex: 0,
            duration: 0.75,
        });
    }
    if (height < -1343 && height > -2243) {
        gsap.to(".wrap01", {
            opacity: 0,
            zIndex: 0,
            duration: 0.75,
        });
        gsap.to(
            ".wrap02",
            {
                opacity: 0,
                zIndex: 0,
                duration: 0.75,
            },
            "<"
        );
        gsap.to(
            ".wrap03",
            {
                opacity: 1,
                zIndex: 2,
                duration: 0.75,
            },
            "<"
        );
        gsap.to(".wrap04", {
            opacity: 0,
            zIndex: 0,
            duration: 0.75,
        });
    }
    if (height < -2243 && height > -3099) {
        gsap.to(".wrap01", {
            opacity: 0,
            zIndex: 0,
            duration: 0.75,
        });
        gsap.to(
            ".wrap02",
            {
                opacity: 0,
                zIndex: 0,
                duration: 0.75,
            },
            "<"
        );
        gsap.to(
            ".wrap03",
            {
                opacity: 0,
                zIndex: 0,
                duration: 0.75,
            },
            "<"
        );
        gsap.to(".wrap04", {
            opacity: 1,
            zIndex: 2,
            duration: 0.75,
        });
    }
    if (height < -3100) {
        gsap.to(".wrap01", {
            opacity: 0,
            zIndex: 0,
            duration: 0.75,
        });
        gsap.to(
            ".wrap02",
            {
                opacity: 0,
                zIndex: 0,
                duration: 0.75,
            },
            "<"
        );
        gsap.to(
            ".wrap03",
            {
                opacity: 0,
                zIndex: 0,
                duration: 0.75,
            },
            "<"
        );
        gsap.to(".wrap04", {
            opacity: 0,
            zIndex: 0,
            duration: 0.75,
        });
    }
  
};
