import { getTime } from "./time"



export
class Show {
      constructor(onNewMoment) {
         this.onNewMoment = onNewMoment;
         this.moments = []
      }
      addMoment({ x, y, sound }) {
         this.moments.push({x, y, time: getTime(), sound})
         this.onNewMoment(this.moments);
      }
}

