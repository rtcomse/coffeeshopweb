import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavBarBrand } from './nav-bar-brand';

describe('NavBarBrand', () => {
  let component: NavBarBrand;
  let fixture: ComponentFixture<NavBarBrand>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NavBarBrand]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NavBarBrand);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
