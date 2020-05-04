import { Directive, Input, ViewContainerRef, TemplateRef } from '@angular/core';

@Directive({
  selector: '[ngVar]',
})
export class VarDirective {
  context: any = {};

  @Input()
  set ngVar(context: any){
    this.context.$implict = this.context.ngVar = context;
    this.updateView();
  }  
  constructor(private vcRef: ViewContainerRef, private templateRef: TemplateRef<any>) { }

  updateView(){
    this.vcRef.clear();
    this.vcRef.createEmbeddedView(this.templateRef, this.context)
    
  }
}
