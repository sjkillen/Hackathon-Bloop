
const ntpClient = require('ntp-client')


const host = prompt("Enter IP/PORT");

const socket = io(host);

import $ from "jquery";


socket.on("connection", d => {
      ntpClient.getNetworkTime("time.nist.gov", 123, function(err, date) {
            const ntime = date.getTime();
            const stime = (new Date).getTime();

            const auds = {}
            for (const sound of d.sounds) {
                  const aud = new Audio(`${host}/sound/${sound}`)
                  aud.preload = true;
                  auds[sound] = aud;
            }

            socket.on("showtime", show => {
                  for (const moment of show) {
                        if (matchCoord(moment)) {
                              setTimeout(() => {
                                    auds[moment.sound].play();
                              }, calcDelay(moment.time));
                        }
                  }
            })

            function matchCoord(moment) {
                  const x = +$("#x").val();
                  const y = +$("#y").val();
                  return moment.x == x && y == moment.y;
            }
            function calcDelay(momtime) {
                  const newstime = (new Date).getTime();
                  const newntime = newstime - stime + ntime;
                  const delay = momtime - newntime;
                  return delay
            }
      });
});
