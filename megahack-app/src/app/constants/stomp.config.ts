import { StompConfig } from '@stomp/ng2-stompjs';
import { WebSocketConfig } from './web.socket.config';

export const stompConfig: StompConfig = {
    url:  WebSocketConfig.uri+"?token="+ localStorage.getItem("access_token"),
    headers: {
    },
    heartbeat_in: 0,
    heartbeat_out: 20000,
    reconnect_delay: 5000,
    debug: false
};