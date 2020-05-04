import { Pipe, PipeTransform } from '@angular/core';
import * as _moment from 'moment';
const moment = _moment;

@Pipe({
  name: 'dateTable',
  pure: true
})
export class DateTablePipe implements PipeTransform {
  
  transform(value: any, ...args: any[]): any {
    let dateValue = moment(new Date(value))
    let now = moment()
    
    let day = dateValue.format('DD');
    let month = dateValue.format('MMMM');
    let year = dateValue.format('YYYY');

    if(now.format('DDMMMMYYYY')==dateValue.format('DDMMMMYYYY')){
      return "Hoje"
    }
    
    if(now.format('YYYY')!=year){
      return `${day} de ${month} de ${year}`
    } else if (now.subtract(1, 'd').format('DDMMMMYYYY')==dateValue.format('DDMMMMYYYY')) {
      return "Ontem"
    } else {
      return `${day} de ${month}`;
    }
  }
  
}
