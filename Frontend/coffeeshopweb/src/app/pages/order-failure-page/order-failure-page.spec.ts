import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderFailurePage } from './order-failure-page';

describe('OrderFailurePage', () => {
  let component: OrderFailurePage;
  let fixture: ComponentFixture<OrderFailurePage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OrderFailurePage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OrderFailurePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
