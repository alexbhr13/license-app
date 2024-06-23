import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileStCardComponent } from './profile-st-card.component';

describe('ProfileStCardComponent', () => {
  let component: ProfileStCardComponent;
  let fixture: ComponentFixture<ProfileStCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfileStCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProfileStCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
