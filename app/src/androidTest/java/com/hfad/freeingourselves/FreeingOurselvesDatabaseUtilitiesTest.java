package com.hfad.freeingourselves;

import android.content.Context;
import android.database.Cursor;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FreeingOurselvesDatabaseUtilitiesTest {

    private Context context = InstrumentationRegistry.getTargetContext();

//    @Before
//    public void setUp() {
//    }
//
//    @After
//    public void finish() {
//    }

    @Test
    public void getTopicTitles() throws Exception {
        ArrayList<String> topics = FreeingOurselvesDatabaseUtilities.getTopicTitles(context);
        if (topics != null) {
            assertEquals("Home", topics.get(0));
            assertEquals("Testosterone and You", topics.get(1));
            assertEquals("Finding Healthcare Allies", topics.get(2));
            assertEquals("Resources", topics.get(3));
            assertEquals("Workouts", topics.get(4));
            assertEquals("About us", topics.get(5));
        } else {
            fail();
        }
    }

    @Test
    public void getSpecificTopic() throws Exception {
        Cursor topicCursor = FreeingOurselvesDatabaseUtilities.getSpecificTopic(context, 6);
        if (topicCursor != null) {
            topicCursor.moveToFirst();
            assertEquals("About us", topicCursor.getString(0));
            assertEquals("about", topicCursor.getString(1));
            assertEquals(0, topicCursor.getInt(2));
        } else {
            fail();
        }
    }

//    @Test
//    public void updateTopicsFavorite() throws Exception {
//        boolean success = FreeingOurselvesDatabaseUtilities.updateTopicsFavorite(context, 1, true);
//        assertEquals(true, success);
//        Cursor topicCursor = FreeingOurselvesDatabaseUtilities.getSpecificTopic(context, 1);
//        topicCursor.moveToFirst();
//        assertEquals(1, topicCursor.getInt(2));
//
//        success = FreeingOurselvesDatabaseUtilities.updateTopicsFavorite(context, 1, false);
//        assertEquals(true, success);
//        topicCursor = FreeingOurselvesDatabaseUtilities.getSpecificTopic(context, 1);
//        topicCursor.moveToFirst();
//        assertEquals(0, topicCursor.getInt(2));
//    }
//
//    @Test
//    public void getFaveTopics() throws Exception {
//        FreeingOurselvesDatabaseUtilities.updateTopicsFavorite(context, 1, true);
//        Cursor topicCursor = FreeingOurselvesDatabaseUtilities.getFaveTopics(context);
//        if (topicCursor != null) {
//            topicCursor.moveToFirst();
//            assertEquals(1, topicCursor.getInt(0));
//            assertEquals("Home", topicCursor.getString(1));
//        } else {
//            Log.d("getFaveTopics test", "oh no");
//        }
//    }

    @Test
    public void getResources() throws Exception {
        ArrayList<String> resources = FreeingOurselvesDatabaseUtilities.getResources(context);
        if (resources != null) {
            assertEquals("Brown Boi Project - Our organization", resources.get(0));
            assertEquals("Therapists of Color - Bay Area", resources.get(1));
        } else {
            fail();
        }
    }

    @Test
    public void getResourceLink() throws Exception {
        String link = FreeingOurselvesDatabaseUtilities.getResourceLink(context, 0);
        assertEquals("http://www.brownboiproject.org/", link);
    }

    @Test
    public void getWorkoutNames() throws Exception {
        ArrayList<String> workouts = FreeingOurselvesDatabaseUtilities.getWorkoutNames(context);
        if (workouts != null) {
            assertEquals("Alternating Bicep Curl: 8-12 reps", workouts.get(0));
            assertEquals("Lying-Down Tricep Extension: 8-12 reps", workouts.get(1));
            assertEquals("V-Ups: 15-25 reps", workouts.get(2));
        } else {
            fail();
        }
    }

//    @Test
//    public void getWorkouts() throws Exception {
//
//    }

    @Test
    public void getSpecificWorkout() throws Exception {
        Cursor workoutCursor = FreeingOurselvesDatabaseUtilities.getSpecificWorkout(context, 6);
        if (workoutCursor != null) {
            workoutCursor.moveToFirst();
            assertEquals("Side Bends: 15-25 reps (each side)", workoutCursor.getString(0));
            assertEquals("Hold weights in each hand. Shift the weight downwards on one side then" +
                    " pull up. Repeat on other side.", workoutCursor.getString(1));
        } else {
            fail();
        }
    }

    @Test
    public void updateWorkoutFavorite() throws Exception {
        boolean success = FreeingOurselvesDatabaseUtilities.updateWorkoutFavorite(context, 1, true);
        assertEquals(true, success);
        Cursor workoutCursor = FreeingOurselvesDatabaseUtilities.getSpecificWorkout(context, 1);
        if (workoutCursor != null) {
            workoutCursor.moveToFirst();
            assertEquals(1, workoutCursor.getInt(4));
        } else {
            fail();
        }

        success = FreeingOurselvesDatabaseUtilities.updateWorkoutFavorite(context, 1, false);
        assertEquals(true, success);
        workoutCursor = FreeingOurselvesDatabaseUtilities.getSpecificWorkout(context, 1);
        if (workoutCursor != null) {
            workoutCursor.moveToFirst();
            assertEquals(0, workoutCursor.getInt(4));
        } else {
            fail();
        }
    }

    @Test
    public void updateWorkoutCount() throws Exception {
        Cursor cursor1 = FreeingOurselvesDatabaseUtilities.getSpecificWorkout(context, 1);
        if (cursor1 != null) {
            cursor1.moveToFirst();
            int initialCount = cursor1.getInt(3);

            boolean success = FreeingOurselvesDatabaseUtilities.updateWorkoutCount(context, 1);
            assertEquals(true, success);

            Cursor cursor2 = FreeingOurselvesDatabaseUtilities.getSpecificWorkout(context, 1);
            if (cursor2 != null) {
                cursor2.moveToFirst();
                assertEquals(++initialCount, cursor2.getInt(3));
            } else {
                fail();
            }
        } else {
            fail();
        }
    }

    @Test
    public void getFaveWorkouts() throws Exception {
        FreeingOurselvesDatabaseUtilities.updateWorkoutFavorite(context, 1, true);
        Cursor workoutCursor = FreeingOurselvesDatabaseUtilities.getFaveWorkouts(context);
        if (workoutCursor != null) {
            workoutCursor.moveToFirst();
            assertEquals(1, workoutCursor.getInt(0));
            assertEquals("Alternating Bicep Curl: 8-12 reps", workoutCursor.getString(1));
        } else {
            fail();
        }
    }

//    @Test
//    public void getHealthcareInfo() throws Exception {
//
//    }

    @Test
    public void getHealthCareQuestions() throws Exception {
        ArrayList<String> questions = FreeingOurselvesDatabaseUtilities.getHealthCareQuestions(context);
        if (questions != null) {
            assertEquals("What services does your office/clinic offer to young people? What ongoing" +
                    " services do you offer to young people as they transition into adulthood?", questions.get(0));
            assertEquals("Are there specific documents required to get care from your office (age" +
                    " range, citizenship status, insurance coverage, parental consent, etc.)?", questions.get(1));
        } else {
            fail();
        }
    }

    @Test
    public void getSpecificHealthcareQuestion() throws Exception {
        Cursor questionCursor = FreeingOurselvesDatabaseUtilities.getSpecificHealthcareQuestion(context, 3);
        if (questionCursor != null) {
            questionCursor.moveToFirst();
            assertEquals("What is the registration/scheduling process and what are your hours" +
                    " of operation?", questionCursor.getString(0));
            assertEquals(null, questionCursor.getString(1));
            assertEquals(0, questionCursor.getInt(1));
        } else {
            fail();
        }
    }

    @Test
    public void updateNotes() throws Exception {
        String text = "test notes";
        boolean success = FreeingOurselvesDatabaseUtilities.updateNotes(context, 1, text);
        assertEquals(true, success);
        Cursor questionCursor = FreeingOurselvesDatabaseUtilities.getSpecificHealthcareQuestion(context, 1);
        if (questionCursor != null) {
            questionCursor.moveToFirst();
            assertEquals(text, questionCursor.getString(1));
        } else {
            fail();
        }
    }

    @Test
    public void updateSaved() throws Exception {
        boolean success = FreeingOurselvesDatabaseUtilities.updateSaved(context, 1, true);
        assertEquals(true, success);
        Cursor questionCursor = FreeingOurselvesDatabaseUtilities.getSpecificHealthcareQuestion(context, 1);
        if (questionCursor != null) {
            questionCursor.moveToFirst();
            assertEquals(1, questionCursor.getInt(2));
        } else {
            fail();
        }

        success = FreeingOurselvesDatabaseUtilities.updateSaved(context, 1, false);
        assertEquals(true, success);
        questionCursor = FreeingOurselvesDatabaseUtilities.getSpecificHealthcareQuestion(context, 1);
        if (questionCursor != null) {
            questionCursor.moveToFirst();
            assertEquals(0, questionCursor.getInt(2));
        } else {
            fail();
        }
    }

    @Test
    public void getSavedHealthcare() throws Exception {
        FreeingOurselvesDatabaseUtilities.updateSaved(context, 1, true);
        Cursor healthcareCursor = FreeingOurselvesDatabaseUtilities.getSavedHealthcare(context);
        if (healthcareCursor != null) {
            healthcareCursor.moveToFirst();
            assertEquals(1, healthcareCursor.getInt(0));
            assertEquals("What services does your office/clinic offer to young people? What" +
                    " ongoing services do you offer to young people as they transition into " +
                    "adulthood?", healthcareCursor.getString(1));
        } else {
            fail();
        }
    }

}