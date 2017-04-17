package com.hfad.freeingourselves;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class FreeingOurselvesDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "freeing_ourselves";
    private static final int DB_VERSION = 0;

    // Table names
    private static final String CHAPTERS = "CHAPTERS";
    private static final String WORKOUTS = "WORKOUTS";
    private static final String HEALTHCARE = "HEALTHCARE";
    private static final String CHALLENGES = "CHALLENGES";
    private static final String GOALS = "GOALS";
    private static final String RESOURCES = "RESOURCES";

    FreeingOurselvesDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDataBase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDataBase(db, oldVersion, newVersion);
    }

    private void updateMyDataBase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            // Create chapters table.
            db.execSQL("CREATE TABLE CHAPTERS ("
                    + "CHAPTER_NUM INTEGER, "
                    + "TITLE TEXT, "
                    + "CONTENT TEXT, "
                    + "FAVE INTEGER, "
                    + "LANG TEXT);");
            populateChapters(db);

            // Create workouts table.
            db.execSQL("CREATE TABLE WORKOUTS ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "WORKOUT_NAME TEXT, "
                    + "DETAILS TEXT, "
                    + "PICTURE_FILE TEXT"
                    + "COUNT INTEGER);");
            populateWorkouts(db);

            // Create healthcare table.
            db.execSQL("CREATE TABLE HEALTHCARE ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "STEP_INFO TEXT, "
                    + "NOTES TEXT, "
                    + "COMPLETED INTEGER);");
            populateHealthcare(db);

            // Create challenges table.
            db.execSQL("CREATE TABLE CHALLENGES ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "THREAT TEXT, "
                    + "SIGNS TEXT, "
                    + "ASK_WHEN TEXT, "
                    + "STRATEGIES TEXT);");
            populateChallenges(db);

            // Create goals table.
            db.execSQL("CREATE TABLE GOALS ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "GOAL_NAME TEXT, "
                    + "INPUTS TEXT, "
                    + "ACTIVITIES TEXT, "
                    + "ASSUMPTIONS TEXT, "
                    + "SHORT_TERM TEXT, "
                    + "LONG_TERM TEXT, "
                    + "GOAL TEXT, "
                    + "COMPLETED INTEGER);");
            populateGoals(db);

            // Create Resources table.
            db.execSQL("CREATE TABLE RESOURCES ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "RESOURCE_NAME TEXT, "
                    + "RESOURCE_DESCRIPTION TEXT, "
                    + "LINK TEXT);");
            populateResources(db);

        }
    }

    private void populateChapters(SQLiteDatabase db) {
        ContentValues chapterOneValues = new ContentValues();
        chapterOneValues.put("CHAPTER_NUM", 1);
        chapterOneValues.put("TITLE", "Chapter 1");
        chapterOneValues.put("CONTENT", "hey it's chapter 1");
        chapterOneValues.put("FAVE", 0);
        chapterOneValues.put("LANG", "en");
        db.insert(CHAPTERS, null, chapterOneValues);
    }

    private void populateWorkouts(SQLiteDatabase db) {
        ContentValues bicepCurlValues = new ContentValues();
        bicepCurlValues.put("WORKOUT_NAME", "Alternating Bicep Curl: 8-12 reps");
        bicepCurlValues.put("DETAILS", "Starting with hands hanging by your sides, slowly curl the " +
                "weight of one arm up. Hold the contraction for a second and then slowly lower. Repeat " +
                "with the other arm.");
        bicepCurlValues.put("PICTURE_FILE", "");
        bicepCurlValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, bicepCurlValues);

        ContentValues tricepExtensionValues = new ContentValues();
        tricepExtensionValues.put("WORKOUT_NAME", "Lying-Down Tricep Extension: 8-12 reps");
        tricepExtensionValues.put("DETAILS", "Lie down on the mat with a weight in each hand. Bring your " +
                "arms above your head and bend your elbows so that the weights are resting on either " +
                "side of your head. Extend your arms so they are straight and then slowly return to " +
                "starting position.");
        tricepExtensionValues.put("PICTURE_FILE", "");
        tricepExtensionValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, tricepExtensionValues);

        ContentValues vUpsValues = new ContentValues();
        vUpsValues.put("WORKOUT_NAME", "V-Ups: 15-25 reps");
        vUpsValues.put("DETAILS", "Lie on your back with arms straight behind head and legs straight. " +
                "Contract abdominals muscles and pull arms and legs up, forming a V. Lower arms " +
                "and legs without touching the ground and bring them quickly back to the V position.");
        vUpsValues.put("PICTURE_FILE", "");
        vUpsValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, vUpsValues);

        ContentValues pushupsValues = new ContentValues();
        pushupsValues.put("WORKOUT_NAME", "Push-Ups: 10-25 reps");
        pushupsValues.put("DETAILS", "Place your hands underneath your shoulders and keep your butt " +
                "down so that your back is straight. Slowly lower your body down by bending your " +
                "arms and then push up. If it’s too difficult, drop down to your knees.");
        pushupsValues.put("PICTURE_FILE", "");
        pushupsValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, pushupsValues);

        ContentValues shrugValues = new ContentValues();
        shrugValues.put("WORKOUT_NAME", "Shrug: 15-25 rep");
        shrugValues.put("DETAILS", "Grasp heavier weights in each hand. In a controlled motion, " +
                "shrug your shoulders upward and then relax. Do not roll your shoulders.");
        shrugValues.put("PICTURE_FILE", "");
        shrugValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, shrugValues);

        ContentValues sideBendsValues = new ContentValues();
        sideBendsValues.put("WORKOUT_NAME", "Side Bends: 15-25 reps (each side)");
        sideBendsValues.put("DETAILS", "Hold weights in each hand. Shift the weight downwards on " +
                "one side then pull up. Repeat on other side.");
        sideBendsValues.put("PICTURE_FILE", "");
        sideBendsValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, sideBendsValues);

        ContentValues shoulderPressValues = new ContentValues();
        shoulderPressValues.put("WORKOUT_NAME", "Shoulder Press: 8-12 reps");
        shoulderPressValues.put("DETAILS", "Bring arms out at a 90o angle with palms facing forward. " +
                "Press upwards until your arms are straight and then slowly return to starting position");
        shoulderPressValues.put("PICTURE_FILE", "");
        shoulderPressValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, shoulderPressValues);

        ContentValues bentOverValues = new ContentValues();
        bentOverValues.put("WORKOUT_NAME", "Bent-Over Rows: 8-12 reps (each side)");
        bentOverValues.put("DETAILS", "Hold weight in one arm and lean forward on the opposite " +
                "leg. Allow the weight to dangle between your forward and back leg. Using the " +
                "muscles of the shoulder, pull the weight up and then slowly lower. Switch to other " +
                "side after completed reps");
        bentOverValues.put("PICTURE_FILE", "");
        bentOverValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, bentOverValues);

        ContentValues diamondPushupsValues = new ContentValues();
        diamondPushupsValues.put("WORKOUT_NAME", "Diamond Push-Up: 8-20 reps");
        diamondPushupsValues.put("DETAILS", "Assume push up position but bring your opens palms " +
                "together in a triangle position underneath your chest. Slowly lower as far as " +
                "possible and then push up to return to starting position.");
        diamondPushupsValues.put("PICTURE_FILE", "");
        diamondPushupsValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, diamondPushupsValues);

        ContentValues legRaisesValues = new ContentValues();
        legRaisesValues.put("WORKOUT_NAME", "Leg Raises: 15-25 reps");
        legRaisesValues.put("DETAILS", "Lie on your back, placing hands underneath your butt for" +
                " support. Keep both legs together and straight, then raise them off the floor 90°." +
                " Slowly lower back down without touching the floor and repeat.");
        legRaisesValues.put("PICTURE_FILE", "");
        legRaisesValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, legRaisesValues);

        ContentValues rdlValues = new ContentValues();
        rdlValues.put("WORKOUT_NAME", "RDL: 8-12 reps");
        rdlValues.put("DETAILS", "With a slight bend in the knees, slowly lower weights while " +
                "bending downward. Keep back straight and core tight while doing so.");
        rdlValues.put("PICTURE_FILE", "");
        rdlValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, rdlValues);

        ContentValues sittingTrunkTwistValues = new ContentValues();
        sittingTrunkTwistValues.put("WORKOUT_NAME", "Sitting Trunk Twist: 15-25 reps");
        sittingTrunkTwistValues.put("DETAILS", "Sitting down, lean back slightly while engaging " +
                "core muscles. Holding a medicine ball in your hands, quickly turn and smash it " +
                "into the ground beside you before turning and repeating the same maneuver on your" +
                " other side.");
        sittingTrunkTwistValues.put("PICTURE_FILE", "");
        sittingTrunkTwistValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, sittingTrunkTwistValues);

        ContentValues lungeValues = new ContentValues();
        lungeValues.put("WORKOUT_NAME", "Lunge: 10-20 reps");
        lungeValues.put("DETAILS", "Step one leg out and slowly bend your knees so that your back" +
                " leg is at a 90 degree angle. Push off the heel and step back to starting position." +
                " Complete a set on one leg and then switch to the other.");
        lungeValues.put("PICTURE_FILE", "");
        lungeValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, lungeValues);

        ContentValues gluteBridgeValues = new ContentValues();
        gluteBridgeValues.put("WORKOUT_NAME", "Glute Bridge: 15-25 reps");
        gluteBridgeValues.put("DETAILS", "Lie on your back with your palms on the floor. Tighten " +
                "your core, press your palms into the ground and raise your butt off the ground. " +
                "Tighten your butt and hold for a second before lowering.");
        gluteBridgeValues.put("PICTURE_FILE", "");
        gluteBridgeValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, gluteBridgeValues);

        ContentValues flutterKickValues = new ContentValues();
        flutterKickValues.put("WORKOUT_NAME", "Flutter Kick: 10-25 reps (each leg)");
        flutterKickValues.put("DETAILS", "Lie on your back and place hands underneath your butt. " +
                "Bring both legs up off the ground and then alternate bringing each leg up then " +
                "returning to starting position.");
        flutterKickValues.put("PICTURE_FILE", "");
        flutterKickValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, flutterKickValues);

        ContentValues splitSquatValues = new ContentValues();
        splitSquatValues.put("WORKOUT_NAME", "Split Squat: 10-25 reps (each leg)");
        splitSquatValues.put("DETAILS", "Step forward with one leg and lean forward, keeping back" +
                " straight. Drop down so your legs are at a 90o angle then push through your heels" +
                " to return to starting position. Switch to other leg after first set is completed.");
        splitSquatValues.put("PICTURE_FILE", "");
        splitSquatValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, splitSquatValues);

        ContentValues buttKickersValues = new ContentValues();
        buttKickersValues.put("WORKOUT_NAME", "Butt Kickers: Stationary: 20-30 reps (each leg);" +
                " Moving: 40 meters x 2");
        buttKickersValues.put("DETAILS", "Performed either stationary or moving. Kick your leg " +
                "backwards so that your foot touches your butt. If moving, swing your arms like " +
                "you are sprinting.");
        buttKickersValues.put("PICTURE_FILE", "");
        buttKickersValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, buttKickersValues);

        ContentValues medicineBallValues = new ContentValues();
        medicineBallValues.put("WORKOUT_NAME", "Medicine Ball Slams: 15-30 reps");
        medicineBallValues.put("DETAILS", "Bring the medicine ball behind your head and use your" +
                " full body to throw the ball into the ground. Catch the ball and quickly repeat.");
        medicineBallValues.put("PICTURE_FILE", "");
        medicineBallValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, medicineBallValues);

        ContentValues bicycleValues = new ContentValues();
        bicycleValues.put("WORKOUT_NAME", "Bicycle: 10-25 reps (each side)");
        bicycleValues.put("DETAILS", "Lying on your back, place hands behind your head and raise " +
                "feet off the ground. Bend one knee and bring it toward your chest. At the same " +
                "time, bring the opposite elbow to touch your knee. Repeat on the other side.");
        bicycleValues.put("PICTURE_FILE", "");
        bicycleValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, bicycleValues);

        ContentValues jumpSquatValues = new ContentValues();
        jumpSquatValues.put("WORKOUT_NAME", "Jump Squat");
        jumpSquatValues.put("DETAILS", "Lower yourself down into a squatting position and then " +
                "spring into the air as high as possible. Land softly while returning to squatting" +
                " position and repeat.");
        jumpSquatValues.put("PICTURE_FILE", "");
        jumpSquatValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, jumpSquatValues);

        ContentValues spidermanValues = new ContentValues();
        spidermanValues.put("WORKOUT_NAME", "Spiderman");
        spidermanValues.put("DETAILS", "Start in a push-up position. Moving forward, reach " +
                "forward with your right hand and take a step with your right foot, keeping your" +
                " butt low and bring your knee up toward your chest. Take another step with your" +
                " left side. Continue forward.");
        spidermanValues.put("PICTURE_FILE", "");
        spidermanValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, spidermanValues);

        ContentValues plankValues = new ContentValues();
        plankValues.put("WORKOUT_NAME", "Plank");
        plankValues.put("DETAILS", "Resting on your elbows with your legs straight behind you, " +
                "hold your core tight and keep your back level.");
        plankValues.put("PICTURE_FILE", "");
        plankValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, plankValues);

        ContentValues explosivePushupsValues = new ContentValues();
        explosivePushupsValues.put("WORKOUT_NAME", "Explosive Push-Ups");
        explosivePushupsValues.put("DETAILS", "Start from a normal push up position. While " +
                "pushing up, explode upward and, if possible, clap your hands before landing" +
                " on your palms.");
        explosivePushupsValues.put("PICTURE_FILE", "");
        explosivePushupsValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, explosivePushupsValues);

        ContentValues sidePlankValues = new ContentValues();
        sidePlankValues.put("WORKOUT_NAME", "Side Plank");
        sidePlankValues.put("DETAILS", "Resting on your forearm and keeping your legs straight " +
                "while on your side, raise your torso off the ground and hold. Hold for 30 " +
                "seconds, then switch sides.");
        sidePlankValues.put("PICTURE_FILE", "");
        sidePlankValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, sidePlankValues);

        ContentValues skiJumpsValues = new ContentValues();
        skiJumpsValues.put("WORKOUT_NAME", "Ski Jumps");
        skiJumpsValues.put("DETAILS", "Start from a squatting position with your hands clenched" +
                " loosely and elbows bent at a 90o angle. Jump laterally and land softly in the" +
                " starting position. Regain balance and jump to the other side.");
        skiJumpsValues.put("PICTURE_FILE", "");
        skiJumpsValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, skiJumpsValues);

        ContentValues crabWalksValues = new ContentValues();
        crabWalksValues.put("WORKOUT_NAME", "Crab Walks");
        crabWalksValues.put("DETAILS", "With your stomach facing upward, hold your body up with " +
                "your hands and feet. Scuttle backwards as fast as you can.");
        crabWalksValues.put("PICTURE_FILE", "");
        crabWalksValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, crabWalksValues);

        ContentValues burpeeValues = new ContentValues();
        burpeeValues.put("WORKOUT_NAME", "Burpee");
        burpeeValues.put("DETAILS", "Start from a standing position. Crouch down and thrust your" +
                " legs out behind you, into a plank or push-up position. Quickly bring your legs " +
                "back to your chest and jump as high as you can. Land softly and immediately repeat.");
        burpeeValues.put("PICTURE_FILE", "");
        burpeeValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, burpeeValues);

        ContentValues upDownPlankValues = new ContentValues();
        upDownPlankValues.put("WORKOUT_NAME", "Up and Down Plank");
        upDownPlankValues.put("DETAILS", "Start plank on your palms. Drop down so that you are" +
                " resting on your elbows one arm at a time and then return to resting on your palms.");
        upDownPlankValues.put("PICTURE_FILE", "");
        upDownPlankValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, upDownPlankValues);
    }

    private void populateHealthcare(SQLiteDatabase db) {
        ContentValues chapterOneValues = new ContentValues();
        chapterOneValues.put("CHAPTER_NUM", 1);
        chapterOneValues.put("TITLE", "Chapter 1");
        chapterOneValues.put("CONTENT", "hey it's chapter 1");
        chapterOneValues.put("FAVE", 0);
        chapterOneValues.put("LANG", "en");
        db.insert(HEALTHCARE, null, chapterOneValues);
    }

    private void populateChallenges(SQLiteDatabase db) {
        ContentValues chapterOneValues = new ContentValues();
        chapterOneValues.put("CHAPTER_NUM", 1);
        chapterOneValues.put("TITLE", "Chapter 1");
        chapterOneValues.put("CONTENT", "hey it's chapter 1");
        chapterOneValues.put("FAVE", 0);
        chapterOneValues.put("LANG", "en");
        db.insert(CHAPTERS, null, chapterOneValues);
    }

    private void populateGoals(SQLiteDatabase db) {

    }

    private void populateResources(SQLiteDatabase db){
        ContentValues resourceValues = new ContentValues();
        resourceValues.put("RESOURCE_NAME", "My Resource name");
        resourceValues.put("RESOURCE_DESCRIPTION", "My Resource description");
        db.insert(RESOURCES, null, resourceValues);
    }
}
