var elements = document.getElementsByName("timer");
var interval = setInterval(function () {
    var e = 0;
    for (var t = 0; t < elements.length; t++) {
        var n = elements[t].innerText || elements[t].textContent;
        var r = n.split(":");
        var i = parseInt(r[0], 10);
        var s = parseInt(r[1], 10);
        if (i == 0 && s == 0) {
            continue
        }
        s -= 1;
        e++;
        if (i < 0) {
            return clearInterval(interval)
        }
        if (i < 10 && i.length != 2) i = "0" + i;
        if (s < 0 && i != 0) {
            i -= 1;
            s = 59
        } else if (s < 10 && length.seconds != 2) s = "0" + s;
        if (i < 10 && i.length != 2) i = "0" + i;
        elements[t].innerHTML = elements[t].innerHTML.replace(n, i + ":" + s)
    }
    if (e == 0) {
        clearInterval(interval)
    }
}, 1e3)


// if ("undefined" == typeof secS) var secS = "с";
// if ("undefined" == typeof secM) var secM = "сек";
// if ("undefined" == typeof minS) var minS = "м";
// if ("undefined" == typeof minM) var minM = "мин";
// if ("undefined" == typeof hourS) var hourS = "ч";
// if ("undefined" == typeof hourM) var hourM = "час";
// if ("undefined" == typeof dayS) var dayS = "д";
// if ("undefined" == typeof dayM) var dayM = "дн";
// if ("undefined" == typeof detailOut) var detailOut = !1;
// if ("undefined" == typeof readyLink) var readyLink = '<a href="' + document.URL + '"></a>';
// var elements = document.getElementsByTagName("span");
//
// function O() {
// }
//
// O.prototype.elem = null, O.prototype.time = 0, O.prototype.now = 0, O.prototype.stop = !1;
// var times = [];
// var workInterval;
// var SEC = 1e3;
// var MIN = 60 * SEC;
// var HOUR = 60 * MIN;
// var DAY = 24 * HOUR;
//
// function parseTimes() {
//     for (var e = 0; e < elements.length; e++) {
//         var r = elements[e];
//         if (r.id && 0 == r.id.indexOf("time_")) {
//             var t = r.id.split("time_")[1];
//             if (isInt(t = parseInt(t))) {
//                 var n = new O;
//                 n.elem = r, n.time = t - 200, n.now = Date.now(), times.push(n)
//             }
//         }
//     }
//     console.log(times.length), workInterval = setInterval(work, SEC)
// }
//
// function work() {
//     if (0 != times.length) {
//         var e = !1;
//         var r = 0;
//         for (var t = 0; t < times.length; t++) {
//             var n = times[t];
//             n.stop || ((r = Math.round((n.time - Date.now() + n.now) / SEC) * SEC)
//             / SEC >= 1 ? (n.elem.innerHTML = detailOut ? getTimeStr0(r) : getTimeStr1(r, 2), e = !0)
//                 : (n.elem.innerHTML = readyLink, n.stop = !0))
//         }
//         e || clearInterval(workInterval)
//     } else clearInterval(workInterval)
// }
//
// function isInt(e) {
//     return "number" == typeof e && e % 1 == 0
// }
//
// function getTimeStr0(e) {
//     var r = 30;
//     return e < MIN ? Math.floor(e / 1e3) + secS : e < HOUR ? Math.floor(e / MIN) + minS : e < r * HOUR ?
//         ">" + Math.floor(e / HOUR) + hourS : ">" + Math.floor(e / DAY) + dayS
// }
//
// function getTimeStr1(e, r) {
//     if (e < 1) return readyLink;
//     var t = 0;
//     var n = 0;
//     var i = 0;
//     var a = 0;
//     e >= 30 * HOUR && (t = Math.floor(e / DAY), e %= DAY), e >= HOUR && (n = Math.floor(e / HOUR), e %= HOUR),
//     e >= MIN && (i = Math.floor(e / MIN), e %= MIN), e >= SEC && (a = Math.floor(e / SEC));
//     var o = 0;
//     if (0 == t && 0 == n && 0 == i && 0 == a) return "1 " + secM;
//     var f = "";
//     return t > 0 && (f += t + " " + (++o == r || n + i + a == 0 ? dayM : dayS), o == r) ? f : n > 0
//     && (f += " " + n + " " + (++o == r || i + a == 0 ? hourM : hourS), o == r) ? f.trim() : i > 0
//     && (f += " " + i + " " + (++o == r || 0 == a ? minM : minS), o == r) ? f.trim() : (a > 0 && (f += " " + a + " " + secM), f.trim())
// }
//
// setTimeout(parseTimes, 1);
