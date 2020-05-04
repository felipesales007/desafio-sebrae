import { Action } from "@ngrx/store";
import { TypeMessage } from './type.message';

export class AppAction implements Action {
  type: TypeMessage
  payload?: any
  info?: any

  constructor(type: TypeMessage, payload){
    this.type = type
    this.payload = payload
  }
}

export class ActionChat implements Action {
  type: TypeMessage;
  payload?: any;

  constructor(t: TypeMessage, p: any) {
    this.type = t;
    this.payload = p;
  }
}

export class RecordsAction implements Action {
  type: TypeMessage
  payload?: any
  month: any
  year: any

  constructor(type: TypeMessage, playload: any, month?, year?) {
    this.type = type
    this.payload = playload
    this.month = month
    this.year = year
  }
}

export class SpinnerAction implements Action {
  type: TypeMessage
  payload?: any

  constructor(type: TypeMessage, playload: any,) {
    this.type = type
    this.payload = playload
  }
}