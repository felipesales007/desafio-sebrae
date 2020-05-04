import { ActionReducerMap, MetaReducer } from '@ngrx/store';
import { environment } from '../../../environments/environment';
import { reducerSpinner, reducerApp, reducerChat} from './reducer';



export interface State {

}

export const reducers: ActionReducerMap<State> = {
  appState: reducerApp,
  chatState: reducerChat,
  spinnerState: reducerSpinner
};


export const metaReducers: MetaReducer<State>[] = !environment.production ? [logger] : [];

export function logger(reducer) {
  return function(state , action: any) {
    // console.log('state', state);
    // console.log('action', action);
    return reducer(state, action);
  };
}
