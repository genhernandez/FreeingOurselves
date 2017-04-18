package com.hfad.freeingourselves;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class FreeingOurselvesDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "freeing_ourselves";
    private static final int DB_VERSION = 1;

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
            addChapter(db, 1, "Chapter 1", "hey it's chapter 1");
            //TODO: add the rest of the chapters

            // Create workouts table.
            db.execSQL("CREATE TABLE WORKOUTS ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "WORKOUT_NAME TEXT, "
                    + "DETAILS TEXT, "
                    + "PICTURE_FILE TEXT"
                    + "COUNT INTEGER);");
            addWorkout(db, "Alternating Bicep Curl: 8-12 reps", "Starting with hands hanging by " +
                    "your sides, slowly curl the weight of one arm up. Hold the contraction for a" +
                    " second and then slowly lower. Repeat with the other arm.");
            addWorkout(db, "Lying-Down Tricep Extension: 8-12 reps", "Lie down on the mat with a" +
                    " weight in each hand. Bring your arms above your head and bend your elbows " +
                    "so that the weights are resting on either side of your head. Extend your " +
                    "arms so they are straight and then slowly return to starting position.");
            addWorkout(db, "V-Ups: 15-25 reps", "Lie on your back with arms straight behind head " +
                    "and legs straight. Contract abdominals muscles and pull arms and legs up, " +
                    "forming a V. Lower arms and legs without touching the ground and bring them" +
                    " quickly back to the V position.");
            addWorkout(db, "Push-Ups: 10-25 reps", "Place your hands underneath your shoulders and" +
                    " keep your butt down so that your back is straight. Slowly lower your body " +
                    "down by bending your arms and then push up. If it’s too difficult, drop down" +
                    " to your knees.");
            addWorkout(db, "Shrug: 15-25 rep", "Grasp heavier weights in each hand. In a controlled" +
                    " motion, shrug your shoulders upward and then relax. Do not roll your shoulders.");
            addWorkout(db, "Side Bends: 15-25 reps (each side)", "Hold weights in each hand. Shift" +
                    " the weight downwards on one side then pull up. Repeat on other side.");
            addWorkout(db, "Shoulder Press: 8-12 reps", "Bring arms out at a 90o angle with palms" +
                    " facing forward. Press upwards until your arms are straight and then slowly" +
                    " return to starting position.");
            addWorkout(db, "Bent-Over Rows: 8-12 reps (each side)", "Hold weight in one arm and" +
                    " lean forward on the opposite leg. Allow the weight to dangle between your" +
                    " forward and back leg. Using the muscles of the shoulder, pull the weight " +
                    "up and then slowly lower. Switch to other side after completed reps.");
            addWorkout(db, "Diamond Push-Up: 8-20 reps", "Assume push up position but bring your" +
                    " open palms together in a triangle position underneath your chest. Slowly " +
                    "lower as far as possible and then push up to return to starting position.");
            addWorkout(db, "Leg Raises: 15-25 reps", "Lie on your back, placing hands underneath " +
                    "your butt for support. Keep both legs together and straight, then raise them" +
                    " off the floor 90°. Slowly lower back down without touching the floor and repeat.");
            addWorkout(db, "RDL: 8-12 reps", "With a slight bend in the knees, slowly lower weights" +
                    " while bending downward. Keep back straight and core tight while doing so.");
            addWorkout(db, "Sitting Trunk Twist: 15-25 reps", "Sitting down, lean back slightly" +
                    " while engaging core muscles. Holding a medicine ball in your hands, quickly" +
                    " turn and smash it into the ground beside you before turning and repeating" +
                    " the same maneuver on your other side.");
            addWorkout(db, "Lunge: 10-20 reps", "Step one leg out and slowly bend your knees so " +
                    "that your back leg is at a 90 degree angle. Push off the heel and step back" +
                    " to starting position. Complete a set on one leg and then switch to the other.");
            addWorkout(db, "Glute Bridge: 15-25 reps", "Lie on your back with your palms on the " +
                    "floor. Tighten your core, press your palms into the ground and raise your " +
                    "butt off the ground. Tighten your butt and hold for a second before lowering.");
            addWorkout(db, "Flutter Kick: 10-25 reps (each leg)", "Lie on your back and place" +
                    " hands underneath your butt. Bring both legs up off the ground and then " +
                    "alternate bringing each leg up then returning to starting position.");
            addWorkout(db, "Split Squat: 10-25 reps (each leg)", "Step forward with one leg and" +
                    " lean forward, keeping back straight. Drop down so your legs are at a 90 degree " +
                    "angle then push through your heels to return to starting position. Switch to " +
                    "other leg after first set is completed.");
            addWorkout(db, "Butt Kickers: Stationary: 20-30 reps (each leg); Moving: 40 meters x 2",
                    "Performed either stationary or moving. Kick your leg backwards so that your" +
                            " foot touches your butt. If moving, swing your arms like you are sprinting.");
            addWorkout(db, "Medicine Ball Slams: 15-30 reps", "Bring the medicine ball behind your " +
                    "head and use your full body to throw the ball into the ground. Catch the " +
                    "ball and quickly repeat.");
            addWorkout(db, "Bicycle: 10-25 reps (each side)", "Lying on your back, place hands " +
                    "behind your head and raise feet off the ground. Bend one knee and bring it " +
                    "toward your chest. At the same time, bring the opposite elbow to touch your" +
                    " knee. Repeat on the other side.");
            addWorkout(db, "Jump Squat", "Lower yourself down into a squatting position and then " +
                    "spring into the air as high as possible. Land softly while returning to squatting" +
                    " position and repeat.");
            addWorkout(db, "Spiderman", "Start in a push-up position. Moving forward, reach " +
                    "forward with your right hand and take a step with your right foot, keeping your" +
                    " butt low and bring your knee up toward your chest. Take another step with your" +
                    " left side. Continue forward.");
            addWorkout(db, "Plank", "Resting on your elbows with your legs straight behind you, " +
                    "hold your core tight and keep your back level.");
            addWorkout(db, "Explosive Push-Ups", "Start from a normal push up position. While " +
                    "pushing up, explode upward and, if possible, clap your hands before landing" +
                    " on your palms.");
            addWorkout(db, "Side Plank", "Resting on your forearm and keeping your legs straight " +
                    "while on your side, raise your torso off the ground and hold. Hold for 30 " +
                    "seconds, then switch sides.");
            addWorkout(db, "Ski Jumps", "Start from a squatting position with your hands clenched" +
                    " loosely and elbows bent at a 90o angle. Jump laterally and land softly in the" +
                    " starting position. Regain balance and jump to the other side.");
            addWorkout(db, "Crab Walks", "With your stomach facing upward, hold your body up with " +
                    "your hands and feet. Scuttle backwards as fast as you can.");
            addWorkout(db, "Burpee", "Start from a standing position. Crouch down and thrust your" +
                    " legs out behind you, into a plank or push-up position. Quickly bring your legs " +
                    "back to your chest and jump as high as you can. Land softly and immediately repeat.");
            addWorkout(db, "Up and Down Plank", "Start plank on your palms. Drop down so that you are" +
                    " resting on your elbows one arm at a time and then return to resting on your palms.");

            // Create healthcare table.
            db.execSQL("CREATE TABLE HEALTHCARE ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "STEP_INFO TEXT, "
                    + "NOTES TEXT, "
                    + "COMPLETED INTEGER);");
            addHealthcareStep(db, "Step one example");
            addHealthcareStep(db, "Step two example");
            //TODO: add the rest of the healthcare steps

            // Create challenges table.
            db.execSQL("CREATE TABLE CHALLENGES ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "THREAT TEXT, "
                    + "SIGNS TEXT, "
                    + "ASK_WHEN TEXT, "
                    + "STRATEGIES TEXT);");
            addChallenge(db, "Sample threat", "Sample warning sign", "Sample when to ask", "Sample strategy");
            //TODO: add the rest of the challenges

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
            addGoal(db, "sample goal name", "sample inputs", "sample activities", "sample " +
                    "assumptions", "sample short term", "sample long term", "sample goal");
            //TODO: add the rest of the goals
        }
        if (oldVersion < 2) {
            // Create Resources table.
            db.execSQL("CREATE TABLE RESOURCES ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "RESOURCE_NAME TEXT, "
                    + "RESOURCE_DESCRIPTION TEXT, "
                    + "LINK TEXT);");
            populateResources(db);
        }
    }

    // Defaults to English and not fave.
    private void addChapter(SQLiteDatabase db, int chapNum, String title, String content) {
        ContentValues chapterValues = new ContentValues();
        chapterValues.put("CHAPTER_NUM", chapNum);
        chapterValues.put("TITLE", title);
        chapterValues.put("CONTENT", content);
        chapterValues.put("FAVE", 0);
        chapterValues.put("LANG", "en");
        db.insert(CHAPTERS, null, chapterValues);
    }

    //TODO: deal with pictures
    private void addWorkout(SQLiteDatabase db, String name, String details) {
        ContentValues workoutValues = new ContentValues();
        workoutValues.put("WORKOUT_NAME", name);
        workoutValues.put("DETAILS", details);
        workoutValues.put("PICTURE_FILE", "");
        workoutValues.put("COUNT", 0);
        db.insert(WORKOUTS, null, workoutValues);
    }

    // Defaults to no notes, not completed.
    private void addHealthcareStep(SQLiteDatabase db, String step) {
        ContentValues healthcareStepValues = new ContentValues();
        healthcareStepValues.put("STEP_INFO", step);
        healthcareStepValues.put("COMPLETED", 0);
        db.insert(HEALTHCARE, null, healthcareStepValues);
    }

    private void addChallenge(SQLiteDatabase db, String threat, String signs, String whenToAsk, String strategies) {
        ContentValues challengeValues = new ContentValues();
        challengeValues.put("THREAT", threat);
        challengeValues.put("SIGNS", signs);
        challengeValues.put("ASK_WHEN", whenToAsk);
        challengeValues.put("STRATEGIES", strategies);
        db.insert(CHALLENGES, null, challengeValues);
    }

    // Defaults to not completed.
    private void addGoal(SQLiteDatabase db, String goalName, String inputs, String activities,
                         String assumptions, String shortTerm, String longTerm, String goal) {
        ContentValues goalValues = new ContentValues();
        goalValues.put("GOAL_NAME", goalName);
        goalValues.put("INPUTS", inputs);
        goalValues.put("ACTIVITIES", activities);
        goalValues.put("ASSUMPTIONS", assumptions);
        goalValues.put("SHORT_TERM", shortTerm);
        goalValues.put("LONG_TERM", longTerm);
        goalValues.put("GOAL", goal);
        goalValues.put("COMPLETED", 0);
    }

    private void populateResources(SQLiteDatabase db){
        ContentValues resourceValues = new ContentValues();
        resourceValues.put("RESOURCE_NAME", "Therapists of Color");
        resourceValues.put("RESOURCE_DESCRIPTION", "Bay Area");
        resourceValues.put("LINK", "http://www.therapistsofcolor.org/directory.html");
        db.insert(RESOURCES, null, resourceValues);

        ContentValues resourceValues2 = new ContentValues();
        resourceValues2.put("RESOURCE_NAME", "National Queer & Trans Therapists of Color Network");
        resourceValues2.put("RESOURCE_DESCRIPTION", "national provider directory of queer and trans therapists of color");
        resourceValues2.put("LINK", "http://www.nqttcn.com/directory");
        db.insert(RESOURCES, null, resourceValues2);
    }
}
