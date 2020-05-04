import { TypeMessage } from 'src/app/provider/reducers/type.message';

export interface WsData {
   type: TypeMessage,
   payload: any
}