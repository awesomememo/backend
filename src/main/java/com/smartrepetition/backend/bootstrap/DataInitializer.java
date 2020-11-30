package com.smartrepetition.backend.bootstrap;

import com.smartrepetition.backend.models.Item;
import com.smartrepetition.backend.models.Progress;
import com.smartrepetition.backend.models.Streak;
import com.smartrepetition.backend.models.User;
import com.smartrepetition.backend.services.UserService;
import com.smartrepetition.backend.utils.DateUtil;
import java.time.ZonedDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


//@Component
@Transactional
public class DataInitializer implements CommandLineRunner {

  private final UserService userService;
  private static final String TESTER_NAME = "tester";

  public DataInitializer(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void run(String... args) {
    if (userService.countUsers() == 0) {
      addUsers();
      addStreaks();
      addItems();
      addProgress();
    }
  }

  private void addUsers() {
    User tester = User.builder()
        .username(TESTER_NAME)
        .email("tester@test.com")
        .password("password")
        .currStreak(3)
        .longestStreak(2)
        .build();
    userService.save(tester);
  }

  private void addStreaks() {
    User tester = userService.getUserByUserName(TESTER_NAME);
    Streak streak1 = Streak.builder().date(DateUtil.date(2020, 11, 25)).build();
    Streak streak2 = Streak.builder().date(DateUtil.date(2020, 11, 27)).build();
    Streak streak3 = Streak.builder().date(DateUtil.date(2020, 11, 28)).build();
    tester.addStreak(streak1).addStreak(streak2).addStreak(streak3);
    userService.save(tester);
  }

  private void addItems() {
    Item testWord1 = Item.builder().name("test word1").description("word1 description")
        .createTime(ZonedDateTime.now()).hint("word1 hint").build();
    testWord1.addProgress(Progress.builder().isPass(false).time(DateUtil.date(2020, 11, 25)).build())
        .addProgress(Progress.builder().isPass(true).time(DateUtil.date(2020, 11, 25)).build())
        .addProgress(Progress.builder().isPass(true).time(DateUtil.date(2020, 11, 27)).build());
    Item testWord2 = Item.builder().name("test word2").description("word2 description")
        .createTime(ZonedDateTime.now()).hint("word2 hint").build();
    testWord2.addProgress(Progress.builder().isPass(true).time(DateUtil.date(2020, 11, 25)).build())
        .addProgress(Progress.builder().isPass(true).time(DateUtil.date(2020, 11, 27)).build());
    User tester = userService.getUserByUserName(TESTER_NAME);
    tester.addItem(testWord1);
    tester.addItem(testWord2);
    userService.save(tester);
  }

  private void addProgress() {
    User tester = userService.getUserByUserName(TESTER_NAME);
    for (Item item : tester.getItems()) {
      item.addProgress(Progress.builder().isPass(true).time(DateUtil.date(2020, 11, 28)).build());
    }
    userService.save(tester);
  }
}
