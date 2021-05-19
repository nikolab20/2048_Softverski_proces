/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package request;

import java.io.Serializable;

/**
 *
 * @author nikolab
 */
public enum RequestOperation implements Serializable {
    LOG_IN,
    SING_UP,
    UPDATE_USER,
    START_GAME,
    END_GAME
}
