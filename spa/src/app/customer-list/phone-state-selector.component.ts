import { Component, Output, EventEmitter } from '@angular/core';
import { PhoneState } from "../phone-state";

@Component({
  selector: 'phone-state-selector',
  template: `
    <select (change)="filterByPhoneNumberState($event.target.value)" id="filterPhoneNumberStateInput">
      <option selected>All one...</option>
      <option *ngFor="let state of keys(phoneStates)" [ngValue]="phoneStates[state]">{{state}}</option>
    </select>
  `
})
export class PhoneStateSelectorComponent   {
  keys = Object.keys;
  phoneStates = PhoneState;

  @Output() searchByPhoneState = new EventEmitter();

  filterByPhoneNumberState(value: string) {
    this.searchByPhoneState.emit(value);
  }
}
  