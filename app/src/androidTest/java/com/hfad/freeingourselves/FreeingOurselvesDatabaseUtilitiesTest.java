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
        assertEquals("Home", topics.get(0));
        assertEquals("Testosterone and You", topics.get(1));
        assertEquals("Finding Healthcare Allies", topics.get(2));
        assertEquals("Resources", topics.get(3));
        assertEquals("Workouts", topics.get(4));
        assertEquals("About us", topics.get(5));
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
            Log.d("getSpecificTopic test", "oh no");
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
        assertEquals("Brown Boi Project - Our organization", resources.get(0));
        assertEquals("Therapists of Color - Bay Area", resources.get(1));
    }

    @Test
    public void getResourceLink() throws Exception {
        String link = FreeingOurselvesDatabaseUtilities.getResourceLink(context, 0);
        assertEquals("http://www.brownboiproject.org/", link);
    }

    @Test
    public void getWorkoutNames() throws Exception {
        ArrayList<String> workouts = FreeingOurselvesDatabaseUtilities.getWorkoutNames(context);
        assertEquals("Alternating Bicep Curl: 8-12 reps", workouts.get(0));
        assertEquals("Lying-Down Tricep Extension: 8-12 reps", workouts.get(1));
        assertEquals("V-Ups: 15-25 reps", workouts.get(2));
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
            Log.d("getSpecificWorkout test", "oh no");
        }
    }

    @Test
    public void updateWorkoutFavorite() throws Exception {

    }

    @Test
    public void updateWorkoutCount() throws Exception {

    }

    @Test
    public void getFaveWorkouts() throws Exception {

    }

//    @Test
//    public void getHealthcareInfo() throws Exception {
//
//    }

    @Test
    public void getHealthCareQuestions() throws Exception {

    }

    @Test
    public void updateNotes() throws Exception {

    }

    @Test
    public void updateSaved() throws Exception {

    }

    @Test
    public void getSavedHealthcare() throws Exception {

    }

}