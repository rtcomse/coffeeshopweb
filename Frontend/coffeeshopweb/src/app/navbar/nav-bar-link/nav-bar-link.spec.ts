import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavBarLink } from './nav-bar-link';

describe('NavBarLink', () => {
  let component: NavBarLink;
  let fixture: ComponentFixture<NavBarLink>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NavBarLink]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NavBarLink);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
