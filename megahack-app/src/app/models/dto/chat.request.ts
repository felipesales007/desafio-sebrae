
import { TypeChat } from './../enums/type.chat'

export class ChatRequest {
  "message": string
  "to": string
  "type": TypeChat

  constructor() {
    this.message = null
    this.type = null
    this.to = null
  }

}
