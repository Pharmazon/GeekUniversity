package ru.shcheglov.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Alexey Shcheglov
 * @version dated 02.02.2019
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class SyncEvent {

    private String id;

}
