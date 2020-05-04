import { TypeChat } from './enums/type.chat'
import { Moment } from 'moment'
import { User } from './user.model'

export class Chat {
  "message": string
  "to": string
  "chatType": TypeChat
  "dateTime": Moment
  "isMe"?: boolean
    
  "user": User
  
  constructor() {
    this.message = null
    this.chatType = null
    this.to = null
    this.dateTime = null
    this.user = null
  }

}
