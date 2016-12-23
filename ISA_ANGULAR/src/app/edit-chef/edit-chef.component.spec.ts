/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { EditChefComponent } from './edit-chef.component';

describe('EditUserComponent', () => {
  let component: EditChefComponent;
  let fixture: ComponentFixture<EditChefComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditChefComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditChefComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
