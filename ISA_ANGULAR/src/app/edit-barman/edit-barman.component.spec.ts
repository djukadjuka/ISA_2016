/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { EditBarmanComponent } from './edit-barman.component';

describe('EditUserComponent', () => {
  let component: EditBarmanComponent;
  let fixture: ComponentFixture<EditBarmanComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditBarmanComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditBarmanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
