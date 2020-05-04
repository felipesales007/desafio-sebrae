import { createFeatureSelector } from '@ngrx/store';
import { ActionChat, AppAction, SpinnerAction } from './actions';
import { AppState, ChatState, SpinnerState } from './states';
import { TypeMessage } from './type.message';
import { Chat } from 'src/app/models/chat.model';

export const apptate: AppState = new AppState();
export const spinnerState: SpinnerState = new SpinnerState();
export const chatState: ChatState = new ChatState();

export function reducerApp(state = apptate, action: AppAction): AppState {  
   switch (action.type) {
      case TypeMessage.CART_ADD:
      return {
         ...state,
         cart: action.payload,
         order: null
      }
      case TypeMessage.APP:
      return {
         ...state,
         info: action.info ? action.info : state.info
      }
      case TypeMessage.REMOVE:
      let teste = state.cart
      teste.orderItems = []
      return {
         ...state,
         cart: teste
      }
      default:
      return state
   } 
}

export function reducerChat(state = chatState, action: ActionChat): ChatState {  
   
   switch (action.type) {
      case TypeMessage.CHAT:
      return {
         ...state,
         messages: [...state.messages, action.payload]
      }
      case TypeMessage.CHAT_ORDER:
      var chat = state.messages.find(c => c.id == action.payload['id'])
      if(chat!=null)
      return {
         ...state,
      }
      return {
         ...state,
         messages: [...state.messages, action.payload]
      }
      case TypeMessage.CHATS:
      return {
         ...state,
         messages: action.payload
      }
      default:
      return state
   } 
}


export function reducerSpinner(state = spinnerState, action: SpinnerAction): SpinnerState {
   switch (action.type) {
      case TypeMessage.SPINNER:
      return {
         isLoading: action.payload
      }
      default:
      return state
   }
   
}

export const chatSelect = createFeatureSelector<ChatState>('chatState');
export const appSelect = createFeatureSelector<AppState>('appState');
export const spinnerSelect = createFeatureSelector<SpinnerState>('spinnerState');
