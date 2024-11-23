package aomine.drawer;

import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.MenuAction;
import raven.drawer.component.menu.MenuEvent;
import raven.drawer.component.menu.SimpleMenuOption;
import raven.drawer.component.menu.data.Item;
import raven.drawer.component.menu.data.MenuItem;
import raven.swing.AvatarIcon;

public class MyDrawerBuilder extends SimpleDrawerBuilder {
  @Override
  public SimpleHeaderData getSimpleHeaderData() {
    return new SimpleHeaderData()
      .setIcon(new AvatarIcon(getClass().getResource("/aomine/images/avatar.jpg"), 60, 60, 999))
      .setTitle("Aomine Daiki")
      .setDescription("aomine@gmail.com");
  }

  @Override
  public SimpleMenuOption getSimpleMenuOption() {
    MenuItem[] menus = new MenuItem[]{
      new Item.Label("MAIN"),
      new Item("Dashboard", "dashboard.svg"),
      new Item("Email", "email.svg")
        .subMenu("Inbox")
        .subMenu("Read")
        .subMenu("Compost"),
      new Item("Chat", "chat.svg"),
      new Item("Calendar", "calendar.svg"),
      new Item.Label("COMPONENT"),
      new Item("Advanced UI", "ui.svg")
        .subMenu("Crooper")
        .subMenu("Owl Carousel")
        .subMenu("Sweet Alert"),
      new Item("forms", "forms.svg")
        .subMenu("Basic Elements")
        .subMenu("Advanced Elements")
        .subMenu("Editors")
        .subMenu("Wizard"),
      new Item.Label("OTHER"),
      new Item("Charts", "chart.svg")
        .subMenu("Apex")
        .subMenu("Flot")
        .subMenu("Sparkline"),
      new Item("Icons", "icon.svg")
        .subMenu("Feather Icons")
        .subMenu("Flag Icons")
        .subMenu("Mdi Icons"),
      new Item("Special", "page.svg")
      .subMenu("Blank page")
      .subMenu("Faq")
      .subMenu("Invoice")
      .subMenu("Profile")
      .subMenu("Pricing")
      .subMenu("Timeline")
    };

    return new SimpleMenuOption()
      .setMenus(menus)
      .setBaseIconPath("aomine/icons/drawer")
      .setIconScale(0.45f)
      .addMenuEvent(new MenuEvent() {
        @Override
        public void selected(MenuAction action, int[] index) {
          System.out.println("Menu selected " + index[0]);
        }
      });
  }

  @Override
  public SimpleFooterData getSimpleFooterData() {
    return new SimpleFooterData()
      .setTitle("Java Swing Drawer")
      .setDescription("version 1.1.0");

  }
}
