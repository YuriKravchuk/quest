package com.quest.model.services;

public enum InteractionResult {
    MONSTER_PRESENT ("Монстр в печері! " + ObjectType.MONSTER.getSymbol()),
    TREASURE_FOUND ("Ти не віриш своїм очам! Ти знаходиш скарб!"),
    NO_MONSTER ("Печера порожня."),
    FLEE_SUCCESS ("Ти втік від монстра, але втікаючи отримав незначне поранення."),
    CLIMB_SUCCESS ("Ти залазиш на високе дерево."),
    DESCEND_SUCCESS ("Ти зліз з дерева."),
    CAVE_EXITED ("Ти вийшов з печери. Потрібно рухатись далі."),
    WEAPON_FOUND ("Ти знайшов меч!"),
    SEE_EXIT("Ти побачив вихід з лісу. Нарешті! \nВихід знаходиться приблизно на "),
    SEE_EXIT_WITH_DESTINATION (""),
    EMPTY_LOCATION ("Ти роздивляєшся наколо. Не бачиш нічого цікавого. Час йти далі."),
    JUMP_FORM_TREE_SUCCESS ("Ти зістрибнув з високого дерева. Чим ти думав? Ти втрачаєш здоров'я"),
    BUY_WEAPON ("Ти купив меч у мандрівника!"),
    NO_MONEY ("Ти нічого не маєш, за що можна купити меч."),
    HAVE_WEAPON_ALREADY ("В тебе вже є меч."),
    HUMAN_KNOW_EXIT ("Мандрівник каже що знає де вихід."),
    HUMAN_DONT_KNOW_EXIT ("Мандрівник каже що не знає де вихід."),
    HUMAN_EMPTY("Ти говорош з мандрівником але не дізнаєшся нічого корисного."),
    HUMAN_HAVE_WEAPON ("Мандрівник каже що має меч."),
    HUMAN_GIVE_EXIT_DIRECTION ("Чудово! Мандрівник тобі вказує шлях на вихід: "),
    TEMP_RESULT(""),
    STOP_TALKING("Ти прємно поспілкувався з мандрівником. Час іти далі."),
    EAT_MUSHROOM ("Ти з'в гриб. Ти почуваєш себе краще. Треба йти далі."),
    EAT_BERRY ("Ти з'в лісові ягоди. Ти почуваєш себе краще. Треба йти далі."),
    EAT_NUTS ("Ти з'в лісові горіхи. Ти почуваєш себе краще. Треба йти далі."),
    DRINK_WATER ("Ти випив води з джерела. Ти почуваєш себе краще. Ти сповнений сил іти далі."),
    HIT_MONSTER ("Ти б'єш монстра! Здоров'я монстра: "),
    KILL_MONSTER ("Ти переміг в цій битві!"),
    SAVE_PRINCESS ("Ти врятував принцесу!")
    ;


    public String getTextDescription() {
        return textDescription;
    }

    InteractionResult(String textDescription) {
        this.textDescription = textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    private String textDescription;
}
